package com.johnathangilday.autograder

import org.scalatest.{Matchers, path}
import com.johnathangilday.autograder.domain.Answer

class ProcessSheetJobSpec extends path.FunSpec with Matchers {

  describe("happy path") {

    it("should process testSheet1.jpg correctly") {
      // GIVEN happy path testSheet1.jpg in test resources
      val in = getClass.getResource("/marked-test-sample.jpg").openStream()

      // WHEN process testSheet1.jpg

      // THEN answers are C, D, B, C, E
      val expectedAnswers = Seq(Answer('C'), Answer('D'), Answer('B'), Answer('C'), Answer('E'))
    }
  }

}
