package tbrown.album

import cats.effect.IO

import org.http4s.Uri

import org.scalatest._

import tbrown.album.RefinedTypes.ValidAlbumId

class AlbumServiceSpec extends WordSpec with Matchers {

  "Give back a photo, given a valid integer" in new context {
    val input = "5"

    val result = service.getAlbumsById.take(1).runLog.unsafeRunSync.headOption.getOrElse(Nil)

    assert(result === List(testPhoto))
  }

  //could use scalacheck for testing here
  "throw an InvalidInt exception if an integer outside of 0-100 is provided" in new context {
    val input = "-1"

    assertThrows[InvalidInt](service.getAlbumsById.take(1).runLog.unsafeRunSync)
  }

  "throw an NoIntegerProvided exception a non-numeric input was provided" in new context {
    val input = "hello"

    assertThrows[NoIntegerProvided.type](service.getAlbumsById.take(1).runLog.unsafeRunSync)
  }

  trait context {
    def input: String

    val testPhoto = Photo(1, 1, "title", "wwww.example.com", "www.example.com")

    val mockAlbumRetrieval = new AlbumRetrievalAlgebra[IO] {
      override def getPhotos(albumId: ValidAlbumId): IO[List[Photo]] = IO(List(testPhoto))
    }

    val mockInputOutput = new InputOutputAlgebra[IO] {
      def readLine: IO[String] = IO(input)
      def writeLine(msg: String): IO[Unit] = IO(())
    }

    val service = new AlbumService[IO](mockInputOutput, mockAlbumRetrieval)
  }
}