package com.johnathangilday.autograder.cv

import org.scalatest.{Matchers, FlatSpec}
import com.johnathangilday.autograder.testutils.TestImgFactory

class CropperSpec extends FlatSpec with Matchers with CropperComponentImpl {

  val cropperImpl = cropper.asInstanceOf[CropperImpl]

  it should "crop answer sheets on 8.5 x 11 paper to include only marks" in {
    val file = TestImgFactory.wholeSheetFile
    val bufImg = cropper.crop(file)

    // can't really test this... will visually inspect with ImgLogger
    ImgLogger.debug(bufImg, "cropped-test")
  }

  it should "calculate sub image dimensions from known values" in {
    // given paper which is 85 x 110 pixels, expect dimensions:
    val expected = cropperImpl.Dimensions(11, 22, 24, 81)

    val actual = cropperImpl.Dimensions.forPixels(85, 110)

    actual should be(expected)
  }
}
