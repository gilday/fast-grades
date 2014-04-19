package com.johnathangilday.autograder

import java.io.File
import scala.annotation.tailrec

trait ExamFilesComponent {

  val files: ExamFiles

  trait ExamFiles {
    def tmp: File
    val base: File
  }
}

trait LocalExamFilesComponent extends ExamFilesComponent {

  override val files: ExamFiles = new LocalExamFiles

  private class LocalExamFiles extends ExamFiles {

    /**
     * Returns a temporary file to write exams to disk for processing
     */
    override def tmp: File = {
      @tailrec
      def nextTmp(i: Int): File = {
        val file = new File(base, i.toString)
        if (file.exists) nextTmp(i + 1) else file
      }
      nextTmp(0)
    }

    override val base: File = new File("files")
  }
}