package com.johnathangilday.autograder

import org.scalatest.{FunSpec, Matchers}
import com.johnathangilday.autograder.testutils.TestImgFactory

class ProcessSheetJobSpec extends FunSpec with Matchers {

  val processor = new SheetProcessor

  describe("process one sheet of marked answers") {

    it("should process detect circles") {
      // GIVEN happy path testSheet1.jpg in test resources
      val img = TestImgFactory.markedTestSample

      // WHEN process marked-test-sample
      processor.processImage(img)

      // THEN nothing for now
    }
  }

}
