package com.artushock.home_work_5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artushock.home_work_5.R
import com.artushock.home_work_5.application.App
import com.artushock.home_work_5.ui.users.UsersScreen
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val navigator = AppNavigator(this, R.id.activity_main_container)

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        App.instance.component.inject(this)

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