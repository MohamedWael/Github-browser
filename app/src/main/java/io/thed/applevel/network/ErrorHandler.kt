package io.thed.applevel.network

interface ErrorHandler {
    var errorMsgStringRes: Int
    var errorMsgString: String
    var throwable: Throwable?
}