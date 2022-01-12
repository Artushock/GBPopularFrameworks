package com.artushock.home_work_5.recycler

import androidx.recyclerview.widget.DiffUtil
import com.artushock.home_work_5.data.models.UserListItem

class DiffUtilsCallBack(
    private val oldUsersList: List<UserListItem>,
    private val newUsersList: List<UserListItem>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldUsersList.size

    override fun getNewListSize() = newUsersList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldUsersList[oldItemPosition].id == newUsersList[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, oldLogin, oldUrl) = oldUsersList[oldItemPosition]
        val (_, newLogin, newUrl) = newUsersList[newItemPosition]

        return oldLogin == newLogin && oldUrl == newUrl
    }
}