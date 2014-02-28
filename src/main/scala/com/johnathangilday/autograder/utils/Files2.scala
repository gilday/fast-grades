package com.johnathangilday.autograder.utils

import java.io.File

/**
 * Missing file utilities
 */
object Files2 {

  def listFiles(dir: File): List[File] = {
    if (!dir.exists())
      throw new IllegalArgumentException("Cannot list files - " + dir.getAbsolutePath + " does not exist")
    if (!dir.isDirectory)
      throw new IllegalArgumentException("Cannot list files - " + dir.getAbsolutePath + " is not a directory")

    val arry = Option(dir.listFiles())
    if (arry.isEmpty)
      List()
    else
      arry.get.toList
  }
}
