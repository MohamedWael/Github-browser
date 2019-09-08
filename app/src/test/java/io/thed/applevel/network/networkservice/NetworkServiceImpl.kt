package io.thed.applevel.network.networkservice

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.thed.applevel.network.ErrorHandler
import io.thed.applevel.network.ErrorHandlerImpl
import io.thed.applevel.network.Retrofit

class NetworkServiceImpl<Response, RestClient> : NetworkService<Response, RestClient> {

    override fun execute(
        observable: Observable<Response>,
        responseCallback: (Response) -> Unit,
        errorCallback: (ErrorHandler) -> Unit
    ): Disposable = observable
        .subscribeOn(Schedulers.io())
        .subscribe({
            responseCallback(it)
        }, { errorCallback(ErrorHandlerImpl(it)) })

    override fun createRestClient(tClass: Class<RestClient>): RestClient {
        return Retrofit.createClient(tClass)
    }
}