package com.artushock.home_work_5.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.artushock.home_work_5.data.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MainDataBase : RoomDatabase() {
    abstract fun getGitHubUserDao(): UserDao
}