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

  def formatPhotos(photos: List[Photo]): String =
    photos.map(p => s"[${p.id}] ${p.title}").mkString("\n")

  def stream(args: List[String], requestShutdown: IO[Unit]): Stream[IO, ExitCode] =
    service
      .getAlbumsById
      .flatMap(p => Stream.eval(cmdLine.writeLine(formatPhotos(p))))
      .onError {
        case NoIntegerProvided => Stream.eval(cmdLine.writeLine("Error: Please enter an integer"))
        case InvalidInt(_) => Stream.eval(cmdLine.writeLine("Error: Please enter an integer between 1 - 100"))
      }
      .repeat
      .drain
}