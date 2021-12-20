package com.artushock.home_work_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artushock.home_work_5.application.App.Companion.navigationHolder
import com.artushock.home_work_5.application.App.Companion.router
import com.artushock.home_work_5.users.UsersScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.activity_main_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        router.navigateTo(UsersScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }
}