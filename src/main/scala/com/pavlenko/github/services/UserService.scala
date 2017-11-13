package com.pavlenko.github.services

import com.pavlenko.github.entities.User
import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._
import scala.concurrent.{Future, ExecutionContext}
import scala.io.Source
import scala.util.{Failure, Success, Try}

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
    val json = Try(Source.fromURL(s"https://api.github.com/search/users?q=language:$id&per_page=5").mkString)
    json match {
      case Success(users) =>
        val parsedUsers = Json.parse(users)
        val people = (parsedUsers \ "items").as[List[(String, String)]]
        val res = people
          .map(_._1)
          .map(login => getUserDetails(login))
          .map(_.mkString)
          .map(Json.parse(_))
          .map(_.validate[User])
          .map(_.get)

        res
      case Failure(e) =>
        List()
    }
  }

  private def getUserDetails(login: String): String = {
    val json = Try(Source.fromURL(s"https://api.github.com/users/$login"))
    json match {
      case Success(user) =>
        user.mkString
      case Failure(e) =>
        ""
    }
  }
}
