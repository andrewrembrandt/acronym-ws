package controllers

import com.google.inject.ImplementedBy
import helpers.Fail

import scala.concurrent.Future

@ImplementedBy(classOf[AcromineAcronymService])
trait AcronymService {
  def retrieve(acronyms: String, resultLimit: Option[Int]): Future[Either[Fail, AcronymResults]]
}

