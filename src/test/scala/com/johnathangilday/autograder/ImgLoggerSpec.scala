package com.johnathangilday.autograder

import org.scalatest.{Matchers, FunSpec}
import com.johnathangilday.autograder.testutils.TestImgFactory
import java.io.File
import com.johnathangilday.autograder.utils.Files2

/**
 * Tests for [[ImgLogger]]
 */
class ImgLoggerSpec extends FunSpec with Matchers {


  private val dir = new File("target/img-logger-spec")
  dir.mkdirs()
  val logger = new ImgLogger(dir)

  describe("ImgLogger debugging aid") {

    it("logs images as files to the log directory") {
      val img = TestImgFactory.markedTestSample
      val beforeCount = countFiles(dir)

      logger.debug(img, "imglogger-spec")

      val afterCount = countFiles(dir)
      afterCount should be(beforeCount + 1)
    }
  }

  private def countFiles(dir: File): Int = {
    Files2.listFiles(dir).size
  }
}
