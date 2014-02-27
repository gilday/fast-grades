name := "Auto Grader"

description := "Automatic multiple choice grading based on OpenCV"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
  "com.google.guava" % "guava" % "16.0.1",
  "org.boofcv" % "ip" % "0.16",
  "org.boofcv" % "io" % "0.16",
  "org.scalatest" %% "scalatest" % "2.0" % "test"
)
