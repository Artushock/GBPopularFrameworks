package com.artushock.home_work_5.di

import android.content.Context
import androidx.room.Room
import com.artushock.home_work_5.data.room.GitHubUserDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(context: Context): GitHubUserDB{
        return Room.databaseBuilder(context, GitHubUserDB::class.java, "github_users.db")
            .build()
    }
}