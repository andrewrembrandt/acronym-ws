package controllers

import com.google.inject.ImplementedBy

import scala.concurrent.Future

@ImplementedBy(classOf[AcromineAcronymService])
trait AcronymService {
  def retrieve(acronyms: String, resultLimit: Option[Int]): Future[AcronymResults]
}

