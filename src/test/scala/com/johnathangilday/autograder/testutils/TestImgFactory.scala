package com.johnathangilday.autograder.testutils

import boofcv.io.image.UtilImageIO
import java.awt.image.BufferedImage
import java.io.{FileOutputStream, File}
import com.google.common.io.Resources
import java.net.URL

/**
 * Helps load known test images from resources
 */
object TestImgFactory {

  def markedFile: File = copyUrlToFile(getClass.getResource("/marked-test-sample.jpg"), "marked-test-sample.jpg")
  def markedBufImg: BufferedImage = UtilImageIO.loadImage(getClass.getResource("/marked-test-sample.jpg"))

  def pencilMarkedBufImg: BufferedImage = UtilImageIO.loadImage(getClass.getResource("/pencil-test-sample.jpg"))

  def wholeSheetFile: File = copyUrlToFile(getClass.getResource("/whole-sheet.jpg"), "whole-sheet.jpg")

  private def copyUrlToFile(url: URL, name: String): File = {
    val file = new File(tmp, name)
    val fos = new FileOutputStream(file)
    Resources.copy(url, fos)
    fos.close()
    file
  }

  private def tmp: File = {
    val tmpPath = System.getProperty("java.io.tmpdir")
    new File(tmpPath)
  }
}
