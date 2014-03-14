package com.johnathangilday.autograder.testutils

import boofcv.io.image.UtilImageIO
import java.awt.image.BufferedImage

/**
 * Helps load known test images from resources
 */
object TestImgFactory {

  def markedTestSample: BufferedImage = UtilImageIO.loadImage(getClass.getResource("/marked-test-sample.jpg"))

  def pencilTestSample: BufferedImage = UtilImageIO.loadImage(getClass.getResource("/pencil-test-sample.jpg"))
}
