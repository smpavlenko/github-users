package com.pavlenko.github.services

import com.pavlenko.github.entities.User
import scala.concurrent.{Future, ExecutionContext}

/**
 * @author sergii.pavlenko
 * @since Nov 5, 2017
 */
class UserService(implicit val executionContext: ExecutionContext) {

  def getUser(id: String): Future[Option[User]] = Future {
    Some(User(id, "username"))
  }
}
