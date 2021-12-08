package com.artushock.geekbrainspopularframeworks

import android.os.Bundle
import com.artushock.geekbrainspopularframeworks.application.App
import com.artushock.geekbrainspopularframeworks.databinding.ActivityMainBinding
import com.artushock.geekbrainspopularframeworks.presenter.MainPresenter
import com.artushock.geekbrainspopularframeworks.navigation.AndroidScreens
import com.artushock.geekbrainspopularframeworks.view.BackButtonListener
import com.artushock.geekbrainspopularframeworks.view.MainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private val navigator = AppNavigator(this, R.id.container)

    private val presenter by moxyPresenter {
        MainPresenter(App.instance.router, AndroidScreens())
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach{
            if (it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}