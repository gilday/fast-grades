package com.johnathangilday.autograder

import com.typesafe.config.{ConfigFactory, Config}

trait SettingsComponent {

  val settings: Config
}

trait SettingsComponentImpl extends SettingsComponent {

  override lazy val settings: Config = ConfigFactory.load()
}
