package com.artushock.home_work_5.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artushock.home_work_5.data.GitHubUser

@Database(entities = [GitHubUser::class], version = 1, exportSchema = false)
abstract class GitHubUserDB : RoomDatabase() {
    abstract fun getGitHubUserDao(): GitHubUserDao
}