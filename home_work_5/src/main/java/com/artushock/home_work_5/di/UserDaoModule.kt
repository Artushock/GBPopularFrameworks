package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.room.MainDataBase
import dagger.Module
import dagger.Provides

@Module
class UserDaoModule {

    @Provides
    fun provideUserDao(db: MainDataBase) = db.getUserDao()

}