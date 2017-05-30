package controllers

import play.api.libs.json.Json

case class AcronymResults(acronym: String, longForms: Seq[LongForm])

object AcronymResults {
  implicit val acronymResultsJson = Json.writes[AcronymResults]
}

case class LongForm(name: String)

object LongForm {
  implicit val longFormJson = Json.writes[LongForm]
}