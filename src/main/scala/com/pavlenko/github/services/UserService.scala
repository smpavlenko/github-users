package com.pavlenko.github.services

import com.pavlenko.github.entities.User
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import scala.concurrent.{Future, ExecutionContext}
import scala.io.Source

/**
 * @author sergii.pavlenko
 * @since Nov 5, 2017
 */
class UserService(implicit val executionContext: ExecutionContext) {

  implicit val personReader: Reads[(String, String)] = (
      (__ \ "login").read[String] and
      (__ \ "avatar_url").read[String]
    ).tupled

  def getUser(id: String): Future[List[User]] = Future {
    val users = Source.fromURL(s"https://api.github.com/search/users?q=language:$id&per_page=5").mkString
    val parsedUsers = Json.parse(users)
    val people = (parsedUsers \ "items").as[List[(String, String)]]

    val res = people
      .map(_._1)
      .map(login => Source.fromURL(s"https://api.github.com/users/$login"))
      .map(_.mkString)
      .map(Json.parse(_))
      .map(_.validate[User])
      .map(_.get)

    res
  }

}
