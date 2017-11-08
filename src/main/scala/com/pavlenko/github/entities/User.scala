package com.pavlenko.github.entities

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

/**
 * @author sergii.pavlenko
 * @since Nov 5, 2017
 */
case class User(username: String, name: Option[String], avatarUrl: Option[String], followersNumber: Option[Long]) {
}

object User {
  implicit val userReads: Reads[User] = (
      (JsPath \ "login").read[String] and
      (JsPath \ "name").readNullable[String] and
      (JsPath \ "avatar_url").readNullable[String] and
      (JsPath \ "followers").readNullable[Long]
    )(User.apply _)
}
