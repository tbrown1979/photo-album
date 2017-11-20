package tbrown.album

import io.circe._
import io.circe.generic.extras.auto._
import io.circe.generic.auto._, io.circe.syntax._


import org.http4s._
import org.http4s.EntityDecoder._
import org.http4s.Query
import org.http4s.client.Client

import org.http4s.circe._

import cats.effect.{Effect, IO}
import fs2._
//import org.http4s.client.blaze.PooledHttp1Client
//import org.http4s.util.ExitCode


object Main extends App {
  import scala.io.StdIn._

  private[this] val logger = org.log4s.getLogger


}