package com.artushock.home_work_2_2.detail

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailView: MvpView {
    fun setLogin(login: String)
    fun setPassword(password: String)
}