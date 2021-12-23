package com.artushock.home_work_5.data.room

import androidx.room.Room
import com.artushock.home_work_5.application.App

object GitHubUserDBFactory {

    private val database: GitHubUserDB by lazy {
        Room.databaseBuilder(App.ContextHolder.context, GitHubUserDB::class.java, "github_users.db")
            .build()
    }

    fun create(): GitHubUserDB = database
}