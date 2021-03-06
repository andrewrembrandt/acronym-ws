package controllers

import javax.inject.Inject

import helpers.Fail

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.ws.WSClient

class AcromineAcronymService @Inject() (ws: WSClient) extends AcronymService{

  override def retrieve(acronym: String, resultLimit: Option[Int]): Future[Either[Fail,AcronymResults]] = {
    val acromineResults = ws.url("http://www.nactem.ac.uk/software/acromine/dictionary.py").withQueryString("sf" -> acronym)

    acromineResults.get
      .flatMap(response => Future {
        val longForm = (response.json \\ "lf").map(jv => jv.as[String]).distinct

        val filteredLongForms = resultLimit match {
          case Some(n) => longForm.take(n)
          case _ => longForm
        }

        Right(AcronymResults(acronym, filteredLongForms.map(longForm => LongForm(longForm))))
      }).recover {
        case cause => Left(Fail("Error requesting from Acromine external service").withEx(cause))
      }
  }
}
