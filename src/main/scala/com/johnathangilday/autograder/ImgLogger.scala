package com.johnathangilday.autograder

import boofcv.core.image.ConvertBufferedImage
import boofcv.struct.image.ImageUInt8
import com.johnathangilday.autograder.exceptions.AppException
import java.awt.image.BufferedImage
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.imageio.ImageIO

/**
 * Debug tool that logs images to disk for manual inspection
 */
object ImgLogger {

  private val instance = new ImgLogger(initDir())

  def setLevelToDebug() = instance.setLevelToDebug()
  def debug(img: BufferedImage, name: String) = instance.debug(img, name)
  def debug(img: ImageUInt8, name: String) = instance.debug(img, name)

  private def initDir(): File = {
    val dir = new File("log")
    if (!dir.exists() && !dir.mkdirs()) {
      throw new AppException("Could not create img log dir")
    }
    dir
  }
}


class ImgLogger(dir: File) {

  private case class Level(level: String)
  private val Debug = Level("debug")

  private var level = Debug

  def setLevelToDebug() {
    level = Debug
  }

  def debug(img: ImageUInt8, name: String) {
    if (level == Debug) {
      val bufImg = ConvertBufferedImage.extractBuffered(img)
      writeBufferedImage(bufImg, name)
    }
  }

  def debug(img: BufferedImage, name: String) {
    writeBufferedImage(img, name)
  }

  private def writeBufferedImage(bufImg: BufferedImage, name: String) {
    ImageIO.write(bufImg, "jpg", new File(dir, timestamp + "." + name + ".jpg"))
  }

  private def timestamp: String = {
    val cal = Calendar.getInstance()
    val format = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss")
    format.format(cal.getTime)
  }
}
