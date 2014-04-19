package com.johnathangilday.autograder

import java.io.File

/**
 * require for persistence dependencies
 */
trait Persistence {

  val files: ExamFiles
}

trait ExamFiles {

  /**
   * Returns a temporary file to write exams to disk for processing
   */
  def tmp: File

  val base: File
}
