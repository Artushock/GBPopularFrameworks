package com.artushock.geekbrainspopularframeworks.navigation

import com.artushock.geekbrainspopularframeworks.model.GithubUser
import com.artushock.geekbrainspopularframeworks.user.UserFragment
import com.artushock.geekbrainspopularframeworks.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AppScreens : IScreens {
    override fun users() =
        FragmentScreen {
            UsersFragment.newInstance()
        }

    override fun user(user: GithubUser) =
        FragmentScreen {
            UserFragment.newInstance(user)
        }
}