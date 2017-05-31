package controllers

import javax.inject.Inject

import helpers.Fail
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc.Action

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class AcronymController @Inject() (acronymService: AcronymService) extends Controller {

  def acronym(a: String, maxResults: Option[Int]) = Action.async { request =>

    val futureAcronymResults = acronymService.retrieve(a, maxResults)

    futureAcronymResults.map(results => results match {
      case Right(acronymResults) => Ok(Json.toJson(acronymResults))
      case Left(error) => handleFailure(error)
    })
  }

  def acronyms(a: List[String], maxResults: Option[Int]) = Action.async { request =>

    val futureAllAcronymResults = Future.sequence(a.map(acronym => acronymService.retrieve(acronym, maxResults)))

    futureAllAcronymResults.map(results => {
      val firstErrorOrAllResults = results collectFirst { case Left(firstFail) => firstFail } toLeft
          { results collect { case Right(allSuccessfulResults) => allSuccessfulResults } }

      firstErrorOrAllResults match {
        case Right(ar) => Ok(Json.toJson(ar))
        case Left(firstError) => handleFailure(firstError)
      }
    })
  }

  def handleFailure(fail: Fail) = {
    fail.getRootException.map(Logger.error(fail.userMessage, _))
    InternalServerError(fail.userMessage)
  }
}
