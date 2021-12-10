package com.artushock.geekbrainspopularframeworks.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.artushock.geekbrainspopularframeworks.databinding.LoginItemBinding

class UserViewHolder(private val vb: LoginItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override var pos = -1

        override fun setLogin(text: String) {
            vb.tvLogin.text = text
        }
    }
