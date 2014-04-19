package com.johnathangilday.autograder

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{ServletHolder, ServletContextHandler}
import org.scalatra.servlet.MultipartConfig

object Main extends App {

  val host = "0.0.0.0"
  val port = Option(System.getenv("PORT")).getOrElse("8080").toInt

  val server = new Server(port)
  val context = new ServletContextHandler()
  val holder = new ServletHolder(new FastGradesServlet with CoreImpl)
  holder.getRegistration.setMultipartConfig(
    MultipartConfig(
      maxFileSize = Some(10 * 1024 * 1024), // max upload 10 megs
      fileSizeThreshold = Some(1 * 1024 * 1024) // write to disk if more than one megabyte
    ).toMultipartConfigElement
  )
  context.addServlet(holder, "/*")
  server.setHandler(context)

  server.start()
  server.join()
}
