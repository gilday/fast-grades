package com.johnathangilday.autograder

import spray.routing.HttpServiceActor

class PingHttpActor extends HttpServiceActor {

  override def receive: Receive = runRoute {
    path("ping") {
      get {
        complete("pong")
      }
    }
  }
}
