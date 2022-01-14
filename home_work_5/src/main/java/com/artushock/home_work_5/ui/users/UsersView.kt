package com.artushock.home_work_5.ui.users

import com.artushock.home_work_5.data.models.UserListItem
import com.artushock.home_work_5.ui.BaseView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UsersView : BaseView {
    fun showUsers(users: List<UserListItem>)
}