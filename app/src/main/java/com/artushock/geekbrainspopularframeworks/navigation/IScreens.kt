package com.artushock.geekbrainspopularframeworks.navigation

import com.artushock.geekbrainspopularframeworks.model.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
}