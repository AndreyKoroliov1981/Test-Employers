package com.korol.employers.app

import android.app.Application
import com.korol.employers.di.AppComponent
import com.korol.employers.di.AppModule
import com.korol.employers.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }

}