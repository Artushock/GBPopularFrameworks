package com.artushock.geekbrainspopularframeworks.presenter

import com.artushock.geekbrainspopularframeworks.navigation.IScreens
import com.artushock.geekbrainspopularframeworks.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(private val router: Router, private val screens: IScreens) :
    MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}