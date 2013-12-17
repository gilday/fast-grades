# Auto Grader

Automated grading application based on OpenCV

## Building

Before building the project, follow OpenCV [java instructions](http://docs.opencv.org/doc/tutorials/introduction/desktop_java/java_dev_intro.html) to build a version of OpenCV for your native platform. Copy the resulting jar and opencv native library to `<project_dir>/lib`

This project is developed against Ubuntu and OpenCV 2.4.7 so the resulting lib folder is:

    lib/
      libopencv_java247.so
      opencv-247.jar

Project uses `sbt` (Scala build tool) for building
