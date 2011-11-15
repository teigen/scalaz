package scalaz.http

import request.{RequestHeaders, Uris, Methods, Lines}
import response.xhtml.Doctypes
import response.{Bodys, ResponseHeaders}

trait Http extends EntityHeaders
  with GeneralHeaders
  with Versions
  with Lines
  with Methods
  with RequestHeaders
  with ResponseHeaders
  with Uris
  with StreamStreamApplications
  with Bodys
  with Doctypes

object Http extends Http
