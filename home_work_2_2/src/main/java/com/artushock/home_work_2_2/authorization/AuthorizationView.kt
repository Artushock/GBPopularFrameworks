package com.artushock.home_work_2_2.authorization

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface AuthorizationView : MvpView {
    fun setLoginText(login: String)

    fun setPasswordText(password: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showLoginError(message: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showPasswordError(message: String)
}