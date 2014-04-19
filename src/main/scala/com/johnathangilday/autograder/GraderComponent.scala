package com.johnathangilday.autograder

import java.io.File
import com.johnathangilday.autograder.exceptions.NotYetImplementedException

trait GraderComponent {

  val grader: Grader

  trait Grader {
    def grade(file: File)
  }
}

trait GraderComponentImpl extends GraderComponent {

  override val grader: Grader = new GraderImpl

  class GraderImpl extends Grader {
    override def grade(file: File): Unit = throw NotYetImplementedException
  }
}
