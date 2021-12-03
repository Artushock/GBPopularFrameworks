package com.artushock.geekbrainspopularframeworks

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.artushock.geekbrainspopularframeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        binding.mainActivityButton1.setOnClickListener(listener)
        binding.mainActivityButton2.setOnClickListener(listener)
        binding.mainActivityButton3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.mainActivityButton1.text = text
            1 -> binding.mainActivityButton2.text = text
            2 -> binding.mainActivityButton3.text = text
        }
    }
}