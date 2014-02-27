package com.johnathangilday.autograder

import java.io.InputStream
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * Processes one marked exam sheet
 */
class SheetProcessor {

  def process(is: InputStream) {
  }

  def readImage(is: InputStream): BufferedImage = ImageIO.read(is);
}
