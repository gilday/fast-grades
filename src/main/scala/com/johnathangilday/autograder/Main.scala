package com.johnathangilday.autograder

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Main extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("auto-grader")

  // create and start our service actor
  val service = system.actorOf(Props[PingHttpActor], "ping-service")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, "localhost", port = 8000)
}
