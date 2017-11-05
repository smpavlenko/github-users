package com.pavlenko.github.resources

import akka.http.scaladsl.server.Route
import com.pavlenko.github.routing.CommonResource
import com.pavlenko.github.services.UserService

/**
 * @author sergii.pavlenko
 * @since Nov 5, 2017
 */
trait UserResource extends CommonResource {

  val userService: UserService

  def userRoutes: Route = pathPrefix("users") {
    path(Segment) { language =>
      get {
        complete(userService.getUser(language))
      }
    }

  }
}