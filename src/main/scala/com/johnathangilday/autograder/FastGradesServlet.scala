package com.johnathangilday.autograder

import org.scalatra.{BadRequest, ScalatraServlet}
import org.scalatra.servlet.{FileItem, FileUploadSupport}
import org.json4s.{DefaultFormats, Formats}
import java.io.File
import org.scalatra.json.JacksonJsonSupport

class FastGradesServlet extends ScalatraServlet with FileUploadSupport with JacksonJsonSupport {
  this: Core =>

  protected implicit val jsonFormats: Formats = DefaultFormats

  val json = formats("json")

  get("/") {
    "It works!"
  }

  post("/files") {
    fileParams.get("exam") match {
      case Some(fileItem) => gradeExamFile(fileItem)
      case None => BadRequest("Missing file")
    }
  }

  def gradeExamFile(fileItem: FileItem) = {
    contentType = json
    val file = save(fileItem)
    grader.grade(file)
  }

  def save(fileItem: FileItem): File = {
    val file = files.tmp
    fileItem.write(file)
    file
  }
}
