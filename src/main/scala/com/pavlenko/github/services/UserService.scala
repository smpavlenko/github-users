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

  def getUser(id: String, page: Option[String], perPage: Option[String]): Future[List[User]] = Future {
    val pageParam = page.getOrElse("1")
    val perPageParam = perPage.getOrElse("5")

    val json = Try(Source.fromURL(s"https://api.github.com/search/users?" +
      s"q=language:$id&page=$pageParam&per_page=$perPageParam").mkString)
    json match {
      case Success(users) =>
        val parsedUsers = Json.parse(users)
        val people = (parsedUsers \ "items").as[List[User]]

        val users1 = people
          .map(_.username)
          .flatMap(getUserDetails(_))
          .map(Json.parse(_))
          .map(_.validate[User])
          .map(_.get)

        val logins = users1.map(_.username)

        users1 ::: people.filter(!logins.contains(_))
      case Failure(e) =>
        List()
    }
  }

  private def getUserDetails(login: String): Option[String] = {
    val json = Try(Source.fromURL(s"https://api.github.com/users/$login").mkString)
    json match {
      case Success(user) =>
        Some(user)
      case Failure(e) =>
        None
    }
  }
}
