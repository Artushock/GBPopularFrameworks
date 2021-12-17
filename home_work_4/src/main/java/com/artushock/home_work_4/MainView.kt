package com.artushock.home_work_4

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MainView : MvpView {
    fun setSquareResult(result: Double)
    fun setSquareRootResult(result: Double)
    fun setCubeResult(result: Double)
    fun setCubeRootResult(result: Double)
}