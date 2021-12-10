package com.artushock.home_work_2_2.detail

import com.artushock.home_work_2_2.data.GithubUser
import moxy.MvpPresenter

class DetailPresenter(private val user: GithubUser) : MvpPresenter<DetailView>() {

    override fun onFirstViewAttach() {
        viewState.setLogin(user.login)
        viewState.setPassword(user.password)
    }

    fun init() {
        // do nothing yet
    }
}