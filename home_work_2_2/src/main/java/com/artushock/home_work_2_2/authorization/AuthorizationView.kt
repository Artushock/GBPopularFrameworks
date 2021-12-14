package com.artushock.home_work_2_2.authorization

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(OneExecutionStateStrategy::class)
interface AuthorizationView : MvpView {

    fun showLoginError(message: String)

    fun showPasswordError(message: String)

}