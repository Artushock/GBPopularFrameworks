package com.artushock.home_work_5.user

import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UserScreen(private val login: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory) =
        UserFragment.newInstance(login)
}