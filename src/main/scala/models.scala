package tbrown.album

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval

import org.http4s.Uri

import scala.util.control.NoStackTrace

object RefinedTypes {
  type ValidAlbumId = Int Refined Interval.ClosedOpen[W.`0`.T, W.`100`.T]
}

case class AlbumId(value: Int)
case class PhotoId(value: Int)
case class Title(value: String)
case class PhotoUrl(value: Uri)
case class ThumbnailUrl(value: Uri)

case class Photo(
  albumId: AlbumId,
  id: PhotoId,
  title: Title,
  url: PhotoUrl,
  thumbnailUrl: ThumbnailUrl)

case object NoIntegerProvided extends RuntimeException with NoStackTrace
case class InvalidInt(msg: String) extends RuntimeException with NoStackTrace