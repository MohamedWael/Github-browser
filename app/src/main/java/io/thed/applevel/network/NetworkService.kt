package io.thed.applevel.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class NetworkService<Response, RestClient> {

    fun execute(
        observable: Observable<Response>,
        responseCallback: (Response) -> Unit,
        errorCallback: (ErrorHandler) -> Unit
    ): Disposable = observable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            responseCallback(it)
        }, { errorCallback(ErrorHandlerImpl(it)) })


    fun createRestClient(tClass: Class<RestClient>): RestClient {
        return Retrofit.createClient(tClass)
    }
}

fun <Response> network(service: NetworkService<Response, RestCleint>.() -> Unit): NetworkService<Response, RestCleint> {
    val networkService = NetworkService<Response, RestCleint>()
    service(networkService)
    return networkService
}