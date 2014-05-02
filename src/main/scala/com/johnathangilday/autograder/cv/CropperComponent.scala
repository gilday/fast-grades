package com.johnathangilday.autograder.cv

import java.io.File
import java.awt.image.BufferedImage
import boofcv.io.image.UtilImageIO

trait CropperComponent {

  val cropper: Cropper

  trait Cropper {
    def crop(file: File): BufferedImage
  }
}

trait CropperComponentImpl extends CropperComponent {

  val cropper: Cropper = new CropperImpl

  class CropperImpl extends Cropper {

    val paperWidthMillis = 230.0 // 8"
    val paperHeightMillis = 296.0 // 11.5"

    // define inches from the top left corner
    val topMarginMillis = 60.0
    val rightMarginMillis = 95.0
    val bottomMarginMillis = 280.0
    val leftMarginMillis = 30.0

    val topMargin = topMarginMillis / paperHeightMillis
    val leftMargin = leftMarginMillis / paperWidthMillis
    val widthPrct = (rightMarginMillis - leftMarginMillis) / paperWidthMillis
    val heightPrct = (bottomMarginMillis - topMarginMillis) / paperHeightMillis

    override def crop(file: File): BufferedImage = {
      val bufImg = loadBufferedImage(file)
      val dims = Dimensions.forPixels(bufImg.getWidth, bufImg.getHeight)
      doCrop(bufImg, dims)
    }

    private def doCrop(img: BufferedImage, dims: Dimensions): BufferedImage = img.getSubimage(dims.x, dims.y, dims.w, dims.h)

    private def loadBufferedImage(file: File): BufferedImage = UtilImageIO.loadImage(file.getAbsolutePath)

    case class Dimensions(x: Int, y: Int, w: Int, h: Int)
    object Dimensions {
      def forPixels(width: Int, height: Int): Dimensions = {
        val x = (leftMargin * width).toInt
        val y = (topMargin * height).toInt
        val w = (widthPrct * width).toInt
        val h = (heightPrct * height).toInt
        Dimensions(x, y, w, h)
      }
    }
  }
}