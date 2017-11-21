package tbrown.album

import cats.effect.Effect
import cats.implicits._
import cats.Monad

import eu.timepit.refined._

import fs2._

import tbrown.album.RefinedTypes.ValidAlbumId

class AlbumService[F[_]: Monad](
  inputOutputAlg: InputOutputAlgebra[F],
  albumRetrievalAlg: AlbumRetrievalAlgebra[F])(implicit F: Effect[F]) {

  val readInput: F[ValidAlbumId] = inputOutputAlg.readLine.flatMap { value =>
    F.catchNonFatal(value.toInt)
      .attempt
      .flatMap(e => F.fromEither(e.leftMap(_ => NoIntegerProvided)))
      .flatMap { n =>
        val validated: Either[String, ValidAlbumId] = refineV(n)
        F.fromEither(validated.leftMap(InvalidInt))
      }
  }

  def getAlbumsById: Stream[F, List[Photo]] =
    Stream.repeatEval(inputOutputAlg.writeLine("What album would you like to see?"))
      .flatMap(_ => Stream.eval(readInput))
      .flatMap(albumId => Stream.eval(albumRetrievalAlg.getPhotos(albumId)))
}
