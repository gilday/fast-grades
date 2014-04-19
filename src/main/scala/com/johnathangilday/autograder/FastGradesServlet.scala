package com.johnathangilday.autograder

import org.scalatra.{BadRequest, ScalatraServlet}
import org.scalatra.servlet.{FileItem, FileUploadSupport}
import java.io.File

class FastGradesServlet extends ScalatraServlet with FileUploadSupport {
  this: Persistence with Core =>

  get("/") {
    "It works!"
  }

  post("/files") {
    fileParams.get("exam") match {
      case Some(file) => file.copy()
      case None => BadRequest("Missing file")
    }
  }

  def gradeExamFile(fileItem: FileItem) = {
    val file = save(fileItem)
    // TODO grade the file
  }

  def save(fileItem: FileItem): File = {
    val file = files.tmp
    fileItem.write(file)
    file
  }
}
