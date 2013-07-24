package controllers

import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream

import org.krysalis.barcode4j.impl.upcean.EAN13Bean
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider

import play.api.mvc.Action
import play.api.mvc.Controller

object Barcodes extends Controller {
  val ImageResolution = 144

  def ean13BarCode(ean: Long, mimeType: String): Array[Byte] = {
    val output = new ByteArrayOutputStream
    val canvas = new BitmapCanvasProvider(output, mimeType, ImageResolution, BufferedImage.TYPE_BYTE_BINARY, false, 0)
    val barcode = new EAN13Bean()
    barcode.generateBarcode(canvas, String.valueOf(ean))
    canvas.finish
    output.toByteArray()
  }

  def barcode(ean: Long) = Action {
    val MimeType = "image/png"
    try {
      val imageData = ean13BarCode(ean, MimeType)
      Ok(imageData).as(MimeType)
    } catch {
      case e: IllegalArgumentException =>
        BadRequest("Couldn't generate bar code. Error:" + e.getMessage())
    }
  }
}