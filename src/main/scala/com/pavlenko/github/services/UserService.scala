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
  def getUser(id: String): Future[List[User]] = Future {
    val url = s"https://api.github.com/search/users?q=language:$id"
    val result = Source.fromURL(url).mkString
    val parsed = Json.parse(result)
    val peoples = (parsed \ "items").validate[List[User]].get
    peoples
  }

}
