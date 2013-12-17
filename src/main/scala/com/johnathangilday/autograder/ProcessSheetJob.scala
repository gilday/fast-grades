package com.johnathangilday.autograder

import com.johnathangilday.autograder.domain.Answer
import com.johnathangilday.autograder.exceptions.AppException
import java.io.InputStream

class ProcessSheetJob(in: InputStream) {

  def process: Seq[Answer] = {
    throw new AppException("Not yet implemented")
  }
}
