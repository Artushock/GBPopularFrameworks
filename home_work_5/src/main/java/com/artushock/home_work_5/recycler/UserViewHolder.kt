package com.artushock.home_work_5.recycler

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.artushock.home_work_5.data.GitHubUserListItem
import com.artushock.home_work_5.databinding.UserListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class UserViewHolder(private val binding: UserListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val context = binding.userListItemAvatarImageView.context

    fun bind(user: GitHubUserListItem, userClickListener: UsersAdapter.OnItemClickListener) {
        binding.userListItemName.text = user.login

        Glide.with(context)
            .load(user.url)
            .listener(getGlideListener())
            .into(binding.userListItemAvatarImageView)

        binding.root.setOnClickListener {
            userClickListener.onUserClick(user)
        }
    }

    private fun getGlideListener() = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean,
        ): Boolean {
            Toast.makeText(context, e?.message, Toast.LENGTH_SHORT).show()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean,
        ): Boolean {
            binding.userListItemAvatarProgressBar.visibility = View.GONE
            return false
        }
    }
}