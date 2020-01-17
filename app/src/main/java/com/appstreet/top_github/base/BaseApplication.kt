package com.appstreet.top_github.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import io.reactivex.plugins.RxJavaPlugins


class BaseApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            logger(AndroidLogger())
           /* modules(listOf(NetworkModule.networkModule, ViewModelModules.viewModules))
            RxJavaPlugins.setErrorHandler{}*/

        }
    }


}