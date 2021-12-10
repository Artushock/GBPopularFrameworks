package com.artushock.home_work_2_2.authorization

import com.artushock.home_work_2_2.application.App
import com.artushock.home_work_2_2.data.GithubUser
import moxy.MvpPresenter

class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {

    private fun showDetailFragment(user: GithubUser) {
        App.instance.router.navigateTo(App.instance.screens.detailScreen(user))
    }


    private fun isPasswordValid(password: String) = when {
        password.isEmpty() -> {
            viewState.showPasswordError("Password is empty")
            false
        }
        password.length < 3 -> {
            viewState.showPasswordError("Password too short")
            false
        }
        password.length > 25 -> {
            viewState.showPasswordError("Password too long")
            false
        }
        else -> true
    }


    private fun isLoginValid(login: String) = when {
        login.isEmpty() -> {
            viewState.showPasswordError("Login is empty")
            false
        }
        login.length < 3 -> {
            viewState.showPasswordError("Login too short")
            false
        }
        login.length > 25 -> {
            viewState.showPasswordError("Login too long")
            false
        }
        else -> true
    }

    fun setUserData(login: String, password: String) {
        if (isLoginValid(login) && isPasswordValid(password)) {
            showDetailFragment(GithubUser(login, password))
        }
    }
}


