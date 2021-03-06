package com.maciekjanusz.vertxplayground

import io.vertx.core.AbstractVerticle

class MainVerticle : AbstractVerticle() {

    override fun start() {
        vertx.createHttpServer()
                .requestHandler { req ->
                    req.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x")
                }.listen(8080)
    }
}