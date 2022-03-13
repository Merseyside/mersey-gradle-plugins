package com.merseyside.gradle

fun Any.log(tag: String = this::class.simpleName ?: "Logger") {
    println("$tag: ${toString()}")
}

fun LoggerExtension.printLog(msg: String) {
    if (debug) println("$TAG: $msg")
}