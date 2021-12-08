package com.artushock.geekbrainspopularframeworks.presenter

import com.artushock.geekbrainspopularframeworks.view.IItemView
import com.artushock.geekbrainspopularframeworks.view.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>