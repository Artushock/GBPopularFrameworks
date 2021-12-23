package com.artushock.home_work_5.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
    }

    companion object {
        private val cicerone: Cicerone<Router> by lazy {
            Cicerone.create()
        }

        val navigationHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router

    }

}

