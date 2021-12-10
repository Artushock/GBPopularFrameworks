package com.artushock.geekbrainspopularframeworks.presenter

import com.artushock.geekbrainspopularframeworks.recyclerview.IItemView
import com.artushock.geekbrainspopularframeworks.recyclerview.UserItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>