package com.johnathangilday.autograder

/**
 * dependencies on core application services
 */
trait Core extends ExamFilesComponent with GraderComponent

trait CoreImpl extends Core with LocalExamFilesComponent with GraderComponentImpl with SheetProcessorComponentImpl
