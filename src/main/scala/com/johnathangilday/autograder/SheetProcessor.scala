package com.johnathangilday.autograder

import boofcv.alg.feature.shapes.ShapeFittingOps
import boofcv.alg.filter.binary.{Contour, BinaryImageOps, ThresholdImageOps}
import boofcv.alg.misc.ImageStatistics
import boofcv.core.image.ConvertBufferedImage
import boofcv.io.image.UtilImageIO
import boofcv.struct.image.{ImageFloat32, ImageUInt8}
import com.johnathangilday.autograder.model.Row
import com.typesafe.scalalogging.slf4j.Logging
import georegression.struct.shapes.EllipseRotated_F64
import java.awt.image.BufferedImage
import java.io.File
import scala.collection.JavaConversions._

/**
 * Processes one marked exam sheet in several steps
 *
 * 1. Convert to Binary image
 * 2. Remove noise from the image to get just the circles
 * 3. Detect circles in the image
 * 4. Sort the circles into rows and columns
 */
class SheetProcessor extends Logging {

  def processFile(file: File) {
    val bufImg = loadBufferedImage(file)
    processImage(bufImg)
  }

  /**
   * Do all steps
   */
  def processImage(bufImg: BufferedImage) {
    ImgLogger.debug(bufImg, "original")
    val binaryNoNoise = removeNoise(convertToBinary(bufImg))
    val circles = findCircles(binaryNoNoise)
    findRows(binaryNoNoise, circles)
  }

  /**
   * Step 1
   */
  def convertToBinary(img: BufferedImage): ImageUInt8 = {
    val input = ConvertBufferedImage.convertFromSingle(img, null, classOf[ImageUInt8])
    val binary = new ImageUInt8(input.width, input.height)
    //    val label = new ImageSInt32(input.width,input.height)
    // create a binary image by thresholding
    ThresholdImageOps.threshold(input, binary, 20, true)
    binary
  }

  /**
   * Step 2
   */
  def removeNoise(binary: ImageUInt8): ImageUInt8 = {
    // remove small blobs through erosion and dilation
    // The null in the input indicates that it should internally declare the work image it needs
    // this is less efficient, but easier to code.
    val filtered = BinaryImageOps.erode8(binary, null)
    BinaryImageOps.dilate8(filtered, null)
  }

  /**
   * Step 3
   */
  def findCircles(binary: ImageUInt8): List[Contour] = {
    val contours = BinaryImageOps.contour(binary, 8, null)
    contours.toList
  }

  /**
   * Step 4
   */
  def findRows(binary: ImageUInt8, circles: List[Contour]): Seq[Row] = {
    //ShapeFittingOps.fitEllipse_I32(c.external, 0, false, null).shape)
    val contourEllipsePairs: List[(Contour, EllipseRotated_F64)] = circles.map(c => (c, ShapeFittingOps.fitEllipse_I32(c.external, 0, false, null).shape))
    val sortedByY = contourEllipsePairs.sortBy(_._2.getCenter.getY)
    var i = 0
    sortedByY.grouped(Row.numChoices).map(g => {
      i = i + 1
      val sortedByX = g.sortBy(_._2.getCenter.getX).map(_._1)
      new Row(i, sortedByX)
    }).toSeq
  }

  private def loadBufferedImage(file: File): BufferedImage = UtilImageIO.loadImage(file.getAbsolutePath)

  // the mean pixel value is often a reasonable threshold when creating a binary image
  private def calculateMeanPixel(img: ImageFloat32): Double = ImageStatistics.mean(img)
}
