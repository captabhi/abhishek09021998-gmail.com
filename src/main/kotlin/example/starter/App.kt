package example.starter

import io.vertx.kotlin.core.Vertx

var vertx = io.vertx.core.Vertx.vertx()

fun main(args:Array<String>) {
  vertx.deployVerticle(MainVerticle())

}

