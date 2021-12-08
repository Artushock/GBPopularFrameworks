package com.artushock.geekbrainspopularframeworks.navigation

import com.artushock.geekbrainspopularframeworks.view.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() =
        FragmentScreen {
            UsersFragment.newInstance()
        }
}