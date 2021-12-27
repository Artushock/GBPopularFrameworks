package com.artushock.home_work_5.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.artushock.home_work_5.di.AppComponent
import com.artushock.home_work_5.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
        instance = this

        component = DaggerAppComponent.builder()
            .setContext(this)
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}

