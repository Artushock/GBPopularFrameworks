package com.artushock.home_work_2_2.navigation

import com.artushock.home_work_2_2.authorization.AuthorizationFragment
import com.artushock.home_work_2_2.data.GithubUser
import com.artushock.home_work_2_2.detail.DetailFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AppScreens : IScreens {

    override fun authorisationScreen() = FragmentScreen {
        AuthorizationFragment.newInstance()
    }

    override fun detailScreen(user: GithubUser) = FragmentScreen {
        DetailFragment.newInstance(user)
    }

}