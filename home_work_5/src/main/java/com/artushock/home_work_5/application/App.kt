package com.artushock.home_work_5.application

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        private val cicerone: Cicerone<Router> by lazy {
            Cicerone.create()
        }

        val navigationHolder get() = cicerone.getNavigatorHolder()
        val router get() = cicerone.router

    }

}

