package tbrown.album

import org.http4s.Uri

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
