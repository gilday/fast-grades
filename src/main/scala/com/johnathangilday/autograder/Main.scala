package com.johnathangilday.autograder

import akka.actor.{Props, ActorSystem}
import akka.io.IO
import spray.can.Http

object Main extends App {

  val host = "0.0.0.0"
  val port = Option(System.getenv("PORT")).getOrElse("8080").toInt
  println("binding to port " + port)

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("auto-grader")

  // create and start our service actor
  val service = system.actorOf(Props[HelloHttpActor], "hello-service")

  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ! Http.Bind(service, host, port)
}
