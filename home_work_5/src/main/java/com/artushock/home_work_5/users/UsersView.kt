package com.artushock.home_work_5.users

import com.artushock.home_work_5.data.models.UserListItem
import moxy.MvpView
import moxy.viewstate.strategy.SingleStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(SingleStateStrategy::class)
interface UsersView : MvpView {
    fun showUsers(users: List<UserListItem>)
    fun showError(message: String)
}