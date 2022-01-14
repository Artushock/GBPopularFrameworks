package com.artushock.home_work_5.ui.user.di

import com.artushock.home_work_5.data.room.MainDataBase
import dagger.Module
import dagger.Provides

@Module
class UserReposDaoModule {

    @UserFragmentScope
    @Provides
    fun provideUserReposDao(db: MainDataBase) = db.getUserReposDao()
}