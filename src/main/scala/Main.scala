package tbrown.album

import cats.effect.IO

import fs2._

import org.http4s.client.blaze.PooledHttp1Client
import org.http4s.util.{ExitCode, StreamApp}

object Main extends StreamApp[IO] {
  val httpClient = PooledHttp1Client[IO]()

  val cmdLine = new CommandLineInputOutputInterpreter
  val photoAlbum = new AlbumRetrievalInterpreter(httpClient)
  val service = new AlbumService(cmdLine, photoAlbum)

  def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    service.getAlbumsById.drain
}