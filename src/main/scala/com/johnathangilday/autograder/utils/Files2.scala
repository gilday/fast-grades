package com.johnathangilday.autograder.utils

import com.johnathangilday.autograder.exceptions.AppException
import java.io.File

/**
 * Missing file utilities
 */
object Files2 {

  def listFiles(dir: File): List[File] = {
    verifyIsDirectory(dir)

    val arry = Option(dir.listFiles())
    if (arry.isEmpty)
      List()
    else
      arry.get.toList
  }

  def mkdirs(dir: File) {
    if (!dir.exists() && !dir.mkdirs())
      throw new AppException("Could not create directories for " + dir.getAbsolutePath)
  }

  private def verifyIsDirectory(dir: File) {
    if (!dir.exists())
      throw new IllegalArgumentException("Cannot list files - " + dir.getAbsolutePath + " does not exist")
    if (!dir.isDirectory)
      throw new IllegalArgumentException("Cannot list files - " + dir.getAbsolutePath + " is not a directory")
  }

}
