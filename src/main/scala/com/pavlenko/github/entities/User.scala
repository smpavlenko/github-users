package com.pavlenko.github.entities

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

/**
 * @author sergii.pavlenko
 * @since Nov 5, 2017
 */
case class User(username: String, avatarUrl: String) {
}

object User {
  implicit val userReads: Reads[User] = (
      (JsPath \ "login").read[String] and
      (JsPath \ "avatar_url").read[String]
    )(User.apply _)
}
