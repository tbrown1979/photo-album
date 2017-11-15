package tbrown.album

import io.circe._
import io.circe.generic.auto._
import io.circe.generic.extras.semiauto._
import io.circe.syntax._

import org.http4s._
import org.http4s.circe._
import org.http4s.client.Client

import cats.effect.{Effect, IO}

trait PhotoAlbumAlgebra[F[_]] {
  def getPhotos(albumId: AlbumId): F[List[Photo]]
}

class PhotoAlbumInterpreter(httpClient: Client[IO]) extends PhotoAlbumAlgebra[IO] {
  val baseUrl = Uri.uri("https://jsonplaceholder.typicode.com/photos")

  def getPhotos(albumId: AlbumId): IO[List[Photo]] = {
    httpClient.expect[List[Photo]](baseUrl.withQueryParam("albumId", albumId.value.toString))(jsonOf[IO, List[Photo]])
  }
}

