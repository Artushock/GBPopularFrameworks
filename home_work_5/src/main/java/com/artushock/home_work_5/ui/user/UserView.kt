package com.artushock.home_work_5.ui.user

import com.artushock.home_work_5.data.models.UserDetail
import com.artushock.home_work_5.ui.BaseView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UserView : BaseView {
    fun showUserDetail(user: UserDetail)
}