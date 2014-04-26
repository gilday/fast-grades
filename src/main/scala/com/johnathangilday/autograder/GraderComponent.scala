package com.johnathangilday.autograder

import java.io.File

trait GraderComponent {

  val grader: Grader

  trait Grader {
    def grade(file: File): Seq[Seq[Boolean]]
  }
}

trait GraderComponentImpl extends GraderComponent {
  self: SheetProcessorComponent =>

  override val grader: Grader = new GraderImpl

  class GraderImpl extends Grader {
    override def grade(file: File): Seq[Seq[Boolean]] = sheetProcessor.process(file)
  }
}
