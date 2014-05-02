package com.johnathangilday.autograder

import com.johnathangilday.autograder.cv.{CropperComponentImpl, GraderComponentImpl, GraderComponent, SheetProcessorComponentImpl}

/**
 * dependencies on core application services
 */
trait Core extends ExamFilesComponent with GraderComponent

trait CoreImpl extends Core with LocalExamFilesComponent with GraderComponentImpl with SheetProcessorComponentImpl with CropperComponentImpl with SettingsComponentImpl
