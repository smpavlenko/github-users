package com.pavlenko.github

import scala.concurrent.ExecutionContext

import akka.http.scaladsl.server.Route

import com.pavlenko.github.resources.UserResource
import com.pavlenko.github.services.UserService

trait RestInterface extends Resources {

  implicit def executionContext: ExecutionContext

  lazy val userService = new UserService

  val routes: Route = userRoutes

}

trait Resources extends UserResource