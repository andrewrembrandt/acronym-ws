package controllers

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc.Action

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class AcronymController @Inject() (acronymService: AcronymService) extends Controller {

  def acronym(a: String, maxResults: Option[Int]) = Action.async { request =>

    val futureAcronymResults = acronymService.retrieve(a, maxResults)

    futureAcronymResults.map(acronymResults => Ok(Json.toJson(acronymResults)))
  }

  def acronyms(a: List[String], maxResults: Option[Int]) = Action.async { request =>

    val futureAllAcronymResults = Future.sequence(a.map(acronym => acronymService.retrieve(acronym, maxResults)))

    futureAllAcronymResults.map(acronymResults => Ok(Json.toJson(acronymResults)))
  }
}
