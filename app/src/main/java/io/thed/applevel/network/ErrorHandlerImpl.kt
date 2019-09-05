package io.thed.applevel.network

class ErrorHandlerImpl(throwable: Throwable) : ErrorHandler {
    override var errorMsgStringRes: Int = 0
    override var errorMsgString: String = ""
    override var throwable: Throwable? = null
}