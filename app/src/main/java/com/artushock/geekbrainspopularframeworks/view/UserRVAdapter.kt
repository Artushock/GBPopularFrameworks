package com.artushock.geekbrainspopularframeworks.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.artushock.geekbrainspopularframeworks.databinding.LoginItemBinding
import com.artushock.geekbrainspopularframeworks.presenter.IUserListPresenter

class UserRVAdapter
    (private val presenter: IUserListPresenter) : RecyclerView.Adapter<UserRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val vb: LoginItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos = -1

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LoginItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount() = presenter.getCount()
}