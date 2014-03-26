package com.johnathangilday.autograder

import spray.routing.HttpServiceActor
import org.json4s.{Formats, DefaultFormats}
import spray.httpx.Json4sSupport

class HelloHttpActor extends HttpServiceActor with Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats

  override def receive: Receive = runRoute {
    path("greeting") {
      get {
        complete {
          Greeting("Hello, World!")
        }
      }
    }
  }
}

case class Greeting(message: String)
