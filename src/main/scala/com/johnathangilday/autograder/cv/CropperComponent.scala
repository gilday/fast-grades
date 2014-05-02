package com.johnathangilday.autograder.cv

import java.io.File
import java.awt.image.BufferedImage

trait CropperComponent {

  val cropper: Cropper

  trait Cropper {
    def crop(file: File): BufferedImage
  }
}

trait CropperComponentImpl extends CropperComponent {

}