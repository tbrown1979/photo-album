package tbrown.album

import cats.Monad
import cats.implicits._
import cats.effect.Effect

import eu.timepit.refined._
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval

import fs2._

import scala.util.control.NoStackTrace

class AlbumService[F[_]: Monad](
  inputAlg: InputOutputAlgebra[F],
  albumRetrievalAlg: AlbumRetrievalAlgebra[F])(implicit F: Effect[F]) {

  type ValidAlbumId = Int Refined Interval.ClosedOpen[W.`0`.T, W.`100`.T]

  val readInput: F[ValidAlbumId] = inputAlg.readLine.flatMap { value =>
    F.catchNonFatal(value.toInt)
      .attempt
      .flatMap(e => F.fromEither(e.leftMap(_ => NoIntegerProvided)))
      .flatMap { n =>
      println(n)
        val validated: Either[String, ValidAlbumId] = refineV(n)
        F.fromEither(validated.leftMap(InvalidInt))
      }
  }

  def getAlbumsById: Stream[F, List[Photo]] = {
    Stream.repeatEval(inputAlg.writeLine("What album would you like to see?"))
      .flatMap(_ => Stream.eval(readInput))
      .flatMap(albumId => Stream.eval(albumRetrievalAlg.getPhotos(albumId)))
  }
}
