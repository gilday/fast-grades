package com.johnathangilday.autograder

import boofcv.core.image.ConvertBufferedImage
import boofcv.gui.binary.VisualizeBinaryData
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
  def debugBinary(img: ImageUInt8, name: String) = instance.debugBinary(img, name)

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
      val imgToConvert =
        if (img.isSubimage) {
          img._createNew(img.width, img.height)
        } else {
          img
        }
      val bufImg = ConvertBufferedImage.extractBuffered(imgToConvert)
      writeBufferedImage(bufImg, name)
    }
  }

  def debug(img: BufferedImage, name: String) {
    if (level == Debug)
      writeBufferedImage(img, name)
  }

  def debugBinary(img: ImageUInt8, name: String) {
    if (level == Debug) {
      val imgToConvert =
        if (img.isSubimage) {
          img._createNew(img.width, img.height)
        } else {
          img
        }
      val bufImg = VisualizeBinaryData.renderBinary(imgToConvert, null)
      writeBufferedImage(bufImg, name)
    }
  }

  private def writeBufferedImage(bufImg: BufferedImage, name: String) {
    val file = getNextFile(name, None)
    ImageIO.write(bufImg, "jpg", file)
  }

  private def getNextFile(name: String, number: Option[Int] = None): File = {
    val file =
      if (number.isDefined)
        new File(dir, timestamp + "." + name + "-" + number.get + ".jpg")
      else
        new File(dir, timestamp + "." + name + ".jpg")
    if (file.exists()) {
      val nextInt = number.getOrElse(0) + 1
      getNextFile(name, Some(nextInt))
    }
    else {
      file
    }
  }

  private def timestamp: String = {
    val cal = Calendar.getInstance()
    val format = new SimpleDateFormat("yyyy-mm-dd-hh-mm-ss")
    format.format(cal.getTime)
  }
}
