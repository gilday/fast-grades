package com.johnathangilday.autograder

import java.io.File
import scala.annotation.tailrec
import com.google.common.io.Files
import com.johnathangilday.autograder.utils.Files2

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
      val file = nextTmp(0)
      Files.touch(file)
      file
    }

    override val base: File = {
      val base = new File("files")
      Files2.mkdirs(base)
      base
    }
  }
}