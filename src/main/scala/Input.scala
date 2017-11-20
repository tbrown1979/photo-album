package tbrown.album

import cats.effect.IO

trait InputOutputAlgebra[F[_]] {
  def readLine: F[String]
  def writeLine(msg: String): F[Unit]
}

class InputOutputInterpreter extends InputOutputAlgebra[IO] {

  def readLine: IO[String] = IO(scala.io.StdIn.readLine)
  def writeLine(msg: String): IO[Unit] = IO(println(msg))
}

