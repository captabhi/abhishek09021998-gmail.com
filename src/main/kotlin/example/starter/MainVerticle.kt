package example.starter

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import java.io.InputStream

class MainVerticle : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>) {
    vertx
      .createHttpServer()
      .requestHandler { req ->
        req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!")
      }
      .listen(8888) { http ->
        if (http.succeeded()) {
          startPromise.complete()
          println("HTTP server started on port 8888")
        } else {
          startPromise.fail(http.cause());
        }
      }

    var mailTemplateStream = (getStreamFromRes("emailVerification.html") as InputStream).reader().readText()
    println(mailTemplateStream)

  }

  fun getStreamFromRes(fileName: String): InputStream? {
    val stream = Object::class.java.getResourceAsStream("/$fileName")
//    println(stream ?: "getStreamFromRes : Null stream, are you sure you are giving correct filename?")
    return stream
  }

}
