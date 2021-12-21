package com.artushock.home_work_5.user

import com.artushock.home_work_5.data.GitHubUserDetail
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UserView : MvpView {
    fun showUserDetail(user: GitHubUserDetail)
    fun showError(message: String)
}