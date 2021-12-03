package com.artushock.geekbrainspopularframeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.artushock.geekbrainspopularframeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), GreetingView {

    private val presenter = Presenter(Model())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attach(this)

        binding.mainActivityButton.setOnClickListener {
            presenter.onButtonClick()
        }
    }

    override fun setGreeting(greeting: String) {
        binding.mainActivityButton.text = greeting
    }
}