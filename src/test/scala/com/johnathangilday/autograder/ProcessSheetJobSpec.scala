package com.johnathangilday.autograder

import org.scalatest.{Matchers, path}
import com.johnathangilday.autograder.domain.Answer

class ProcessSheetJobSpec extends path.FunSpec with Matchers {

  describe("process one sheet of marked answers") {

    it("should process detect circles") {
      // GIVEN happy path testSheet1.jpg in test resources
      val in = getClass.getResource("/marked-test-sample.jpg").openStream()

      // WHEN process marked-test-sample

    }
  }

}
