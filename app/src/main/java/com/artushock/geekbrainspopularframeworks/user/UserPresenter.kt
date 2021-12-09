package com.artushock.geekbrainspopularframeworks.user

import com.artushock.geekbrainspopularframeworks.model.GithubUser
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val user: GithubUser, private val router: Router) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setLoginName(user.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}