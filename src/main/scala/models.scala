package tbrown.album

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval

import scala.util.control.NoStackTrace

object RefinedTypes {
  type ValidAlbumId = Int Refined Interval.ClosedOpen[W.`0`.T, W.`100`.T]
}

case class Photo(
  albumId: Int,
  id: Int,
  title: String,
  url: String,
  thumbnailUrl: String)

case object NoIntegerProvided extends RuntimeException with NoStackTrace
case class InvalidInt(msg: String) extends RuntimeException with NoStackTrace