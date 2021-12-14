package com.artushock.home_work_2_2.authorization

import com.artushock.home_work_2_2.application.App
import com.artushock.home_work_2_2.data.GithubUser
import moxy.MvpPresenter

class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {

    private val repository by lazy { App.instance.githubUsersRepository }
    private val router by lazy { App.instance.router }
    private val screens by lazy { App.instance.screens }

    fun signIn(login: String, password: String) {
        if (isLoginValid(login) && isPasswordValid(password)) {
            val users = mutableListOf<String>()
            repository.getUsers().filter {
                it.login == login
            }.subscribe { users.add(it.login) }

            if (users.isNotEmpty()) {
                repository.getUserByName(login).subscribe { user ->
                    checkPassword(user, password)
                }
            } else {
                viewState.showLoginError("User $login is not registered")
            }
        }
    }

    private fun checkPassword(user: GithubUser, password: String) {
        if (user.password == password) {
            showDetailFragment(user, "Welcome ${user.login}")
        } else {
            viewState.showPasswordError("Wrong password!")
        }
    }

    fun register(login: String, password: String) {
        if (isLoginValid(login) && isPasswordValid(password)) {
            val user = GithubUser(login, password)
            repository.addUser(user)
            showDetailFragment(user, "User $login added to the storage")
        }
    }

    private fun showDetailFragment(user: GithubUser, message: String) {
        router.navigateTo(screens.detailScreen(user, message))
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
            viewState.showLoginError("Login is empty")
            false
        }
        login.length < 3 -> {
            viewState.showLoginError("Login too short")
            false
        }
        login.length > 25 -> {
            viewState.showLoginError("Login too long")
            false
        }
        else -> true
    }
}


