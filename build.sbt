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
  "org.scalatra" %% "scalatra" % "2.2.2",
  "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106",
  "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "provided"
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