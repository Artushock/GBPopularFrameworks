package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.room.MainDataBase
import dagger.Module
import dagger.Provides

@Module
class UserDaoModule {

    @Provides
    fun provideGitHubUserDao(db: MainDataBase) = db.getGitHubUserDao()

}