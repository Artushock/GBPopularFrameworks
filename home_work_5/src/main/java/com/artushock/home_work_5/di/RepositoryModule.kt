package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.GitHubUserRepository
import com.artushock.home_work_5.data.GitHubUsersRepositoryImpl
import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.artushock.home_work_5.data.room.GitHubUserDB
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Reusable
    @Provides
    fun provideRepository(api: GitHubApi, db: GitHubUserDB): GitHubUserRepository {
        return GitHubUsersRepositoryImpl(api, db)
    }
}