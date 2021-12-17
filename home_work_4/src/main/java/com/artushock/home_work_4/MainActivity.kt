package com.artushock.home_work_4

import android.os.Bundle
import android.widget.Toast
import com.artushock.home_work_4.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import java.math.RoundingMode

const val AMOUNT_NUMBERS_AFTER_POINT = 3

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.subscribe()
        initButton()

    }

    private fun initButton() {
        binding.activityMainSquareButton.setOnClickListener {
            val number = binding.activityMainEditText.text.toString()
            val regex = Regex("""[+-]?([0-9]*[.])?[0-9]+""")

            if (number.matches(regex)) {
                presenter.calculate(number.toDouble())
            } else {
                Toast.makeText(this, "Wrong number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun setSquareResult(result: Double) {
        binding.activityMainSquareTextView.text = roundNumber(result).toString()
    }

    override fun setSquareRootResult(result: Double) {
        binding.activityMainSquareRootTextView.text = roundNumber(result).toString()
    }

    override fun setCubeResult(result: Double) {
        binding.activityMainCubeTextView.text = roundNumber(result).toString()
    }

    override fun setCubeRootResult(result: Double) {
        binding.activityMainCubeRootTextView.text = roundNumber(result).toString()
    }

    private fun roundNumber(result: Double) =
        result.toBigDecimal().setScale(AMOUNT_NUMBERS_AFTER_POINT, RoundingMode.UP).toDouble()

}