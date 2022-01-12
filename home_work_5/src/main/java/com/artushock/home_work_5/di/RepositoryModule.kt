package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.repositories.UsersRepository
import com.artushock.home_work_5.data.repositories.UsersRepositoryImpl
import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.artushock.home_work_5.data.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {

    @Reusable
    @Provides
    fun provideRepository(api: GitHubApi, dao: UserDao): UsersRepository {
        return UsersRepositoryImpl(api, dao)
    }
}