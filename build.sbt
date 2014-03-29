import com.typesafe.sbt.SbtStartScript
import sbt.Keys._
import sbt.TaskKey

name := "Auto Grader"

description := "Automatic multiple choice grading based on OpenCV"

scalaVersion := "2.10.3"

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "16.0.1",
  "org.boofcv" % "ip" % "0.16",
  "org.boofcv" % "io" % "0.16",
  "org.boofcv" % "visualize" % "0.16",
  "ch.qos.logback" % "logback-classic" % "1.1.1",
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "com.typesafe" %% "scalalogging-slf4j" % "1.0.1",
  "io.spray" % "spray-routing" % "1.3.1",
  "io.spray" % "spray-can" % "1.3.1",
  "io.spray" % "spray-httpx" % "1.3.1",
  "org.json4s" %% "json4s-native" % "3.2.8",
  "com.typesafe.akka" %% "akka-actor" % "2.3.0"
)

seq(SbtStartScript.startScriptForClassesSettings: _*)

TaskKey[Unit]("grunt", "Kicks off a Grunt.js build and stores the built front-end in target/webapp") <<= streams map { (s: TaskStreams) =>
  val log = s.log
  val code = "grunt build" ! log
  code match {
    case 0 => log.info("built webapp is in target/webapp")
    case _ => throw new Exception("Grunt.js build failed")
  }
}