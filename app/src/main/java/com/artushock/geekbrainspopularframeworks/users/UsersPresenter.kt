package com.artushock.geekbrainspopularframeworks.users

import com.artushock.geekbrainspopularframeworks.application.App
import com.artushock.geekbrainspopularframeworks.model.GithubUser
import com.artushock.geekbrainspopularframeworks.model.GithubUserRepo
import com.artushock.geekbrainspopularframeworks.presenter.IUserListPresenter
import com.artushock.geekbrainspopularframeworks.recyclerview.UserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUserRepo,
    private val router: Router,
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = { userItemView ->
            val user = usersRepo.getUserByIndex(userItemView.pos)
            router.navigateTo(App.instance.screens.user(user))
        }
    }

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}