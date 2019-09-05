package io.thed.applevel.application

import android.app.Application
import io.thed.applevel.BASE_API
import io.thed.applevel.network.Retrofit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Retrofit.init(BASE_API)
    }
}