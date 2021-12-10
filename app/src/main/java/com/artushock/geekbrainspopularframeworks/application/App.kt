package com.artushock.geekbrainspopularframeworks.application

import android.app.Application
import com.artushock.geekbrainspopularframeworks.navigation.AppScreens
import com.artushock.geekbrainspopularframeworks.navigation.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigationHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    val screens : IScreens by lazy {
        AppScreens()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}