package tbrown.album

import cats.effect.IO

import io.circe.generic.auto._

import org.http4s._
import org.http4s.circe._
import org.http4s.client.Client

trait AlbumRetrievalAlgebra[F[_]] {
  def getPhotos(albumId: RefinedTypes.ValidAlbumId): F[List[Photo]]
}

class AlbumRetrievalInterpreter(httpClient: Client[IO]) extends AlbumRetrievalAlgebra[IO] {
  val baseUrl = Uri.uri("https://jsonplaceholder.typicode.com/photos")

  def getPhotos(albumId: RefinedTypes.ValidAlbumId): IO[List[Photo]] = {
    httpClient.expect[List[Photo]](baseUrl.withQueryParam("albumId", albumId.value.toString))(jsonOf[IO, List[Photo]])
  }
}