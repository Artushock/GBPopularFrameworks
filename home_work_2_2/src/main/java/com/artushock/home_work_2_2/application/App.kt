package com.artushock.home_work_2_2.application

import android.app.Application
import com.artushock.home_work_2_2.navigation.AppScreens
import com.artushock.home_work_2_2.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val screens: IScreens by lazy {
        AppScreens()
    }

    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}