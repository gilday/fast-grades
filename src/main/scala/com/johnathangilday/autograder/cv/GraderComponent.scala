package com.johnathangilday.autograder.cv

import java.io.File

trait GraderComponent {

  val grader: Grader

  trait Grader {
    def grade(file: File): Seq[Seq[Boolean]]
  }
}

trait GraderComponentImpl extends GraderComponent {
  self: SheetProcessorComponent with CropperComponent =>

  override val grader: Grader = new GraderImpl

  class GraderImpl extends Grader {
    override def grade(file: File): Seq[Seq[Boolean]] = {
      val cropped = cropper.crop(file)
      sheetProcessor.process(cropped)
    }
  }
}
