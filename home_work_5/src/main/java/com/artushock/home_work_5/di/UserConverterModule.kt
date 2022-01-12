package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.UserConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserConverterModule{

    @Singleton
    @Provides
    fun provideUserConverter() = UserConverter()
}