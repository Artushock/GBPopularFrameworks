package com.artushock.home_work_2_2.navigation

import com.artushock.home_work_2_2.data.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun authorisationScreen(): Screen
    fun detailScreen(user: GithubUser): Screen
}