package com.artushock.geekbrainspopularframeworks.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artushock.geekbrainspopularframeworks.databinding.LoginItemBinding
import com.artushock.geekbrainspopularframeworks.presenter.IUserListPresenter

class UserRVAdapter
    (private val presenter: IUserListPresenter) : RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(LoginItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()
}