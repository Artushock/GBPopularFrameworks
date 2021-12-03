package com.artushock.geekbrainspopularframeworks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.artushock.geekbrainspopularframeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainActivityButton1.setOnClickListener{
            presenter.counterClick(0)
        }
        binding.mainActivityButton2.setOnClickListener{
            presenter.counterClick(1)
        }
        binding.mainActivityButton3.setOnClickListener{
            presenter.counterClick(2)
        }

    }

    override fun setFirstButtonText(text: String) {
        binding.mainActivityButton1.text = text
    }

    override fun setSecondButtonText(text: String) {
        binding.mainActivityButton2.text = text
    }

    override fun setThirdButtonText(text: String) {
        binding.mainActivityButton3.text = text
    }


}