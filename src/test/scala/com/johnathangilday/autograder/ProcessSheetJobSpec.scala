package com.johnathangilday.autograder

import org.scalatest.{Matchers, path}
import com.johnathangilday.autograder.domain.Answer
import org.opencv.core.Core

class ProcessSheetJobSpec extends path.FunSpec with Matchers {

  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
  println(String.format("Loaded OpenCV %s", Core.VERSION))

  describe("happy path") {

    it("should process testSheet1.jpg correctly") {
      // GIVEN happy path testSheet1.jpg in test resources
      val in = getClass.getResource("/testSheet1.jpg").getPath
      val job = new ProcessSheetJob(in)

      // WHEN process testSheet1.jpg
      val answers = job.process

      // THEN answers are C, D, B, C, E
      val expectedAnswers = Seq(Answer('C'), Answer('D'), Answer('B'), Answer('C'), Answer('E'))
      answers should contain theSameElementsAs expectedAnswers
    }
  }

}
