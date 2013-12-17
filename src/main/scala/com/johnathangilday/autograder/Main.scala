package com.johnathangilday.autograder

import org.opencv.core.Core

object Main extends App {

  System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
  println(String.format("Loaded OpenCV %s", Core.VERSION))

}
