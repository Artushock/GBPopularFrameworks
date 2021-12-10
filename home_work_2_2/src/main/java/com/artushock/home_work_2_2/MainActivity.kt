package com.artushock.home_work_2_2

import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.artushock.home_work_2_2.application.App
import com.artushock.home_work_2_2.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.router.navigateTo(App.instance.screens.authorisationScreen())
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }
}