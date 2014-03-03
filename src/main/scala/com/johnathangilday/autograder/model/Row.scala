package com.johnathangilday.autograder.model

import boofcv.alg.filter.binary.Contour

object Row {
  /**
   * Number of choices is hard-coded at 4 right now: A, B, C, D TODO work with Product Owner to define this number
   */
  val numChoices = 4
}

/**
 * Represents one Row of [[boofcv.alg.filter.binary.Contour]] while processing a sheet
 */
class Row(val problem: Int, val circles: Seq[Contour]) {

}
