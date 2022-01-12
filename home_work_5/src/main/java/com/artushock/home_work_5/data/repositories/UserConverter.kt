package com.artushock.home_work_5.data.repositories

import com.artushock.home_work_5.data.models.User
import com.artushock.home_work_5.data.models.UserDetail
import com.artushock.home_work_5.data.models.UserListItem

class UserConverter() {

    fun convertUserListToUserItemList(
        userLists: List<User>,
    ): List<UserListItem> {
        val userItemList = ArrayList<UserListItem>()
        for (user in userLists) {
            userItemList.add(convertUserToUserItem(user))
        }
        return userItemList
    }

    fun convertUserToUserDetail(
        user: User,
    ) = UserDetail(
        user.id,
        user.login,
        user.avatarUrl,
        user.type,
        user.siteAdmin
    )

    private fun convertUserToUserItem(
        user: User,
    ) = UserListItem(
        user.id,
        user.login,
        user.avatarUrl
    )
}