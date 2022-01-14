package com.artushock.home_work_5.ui

import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface BaseView: MvpView {
    fun showError(message: String, tumbler: Boolean)
    fun displayProgress(tumbler: Boolean)
}