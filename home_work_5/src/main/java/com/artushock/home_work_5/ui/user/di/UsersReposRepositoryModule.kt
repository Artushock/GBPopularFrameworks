package com.artushock.home_work_5.ui.user.di

import com.artushock.home_work_5.data.repositories.UsersReposRepository
import com.artushock.home_work_5.data.repositories.UsersReposRepositoryImpl
import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.artushock.home_work_5.data.room.UserReposDao
import dagger.Module
import dagger.Provides

@Module
class UsersReposRepositoryModule {

    @UserFragmentScope
    @Provides
    fun provideUsersReposRepository(api: GitHubApi, dao: UserReposDao): UsersReposRepository {
        return UsersReposRepositoryImpl(api, dao)
    }
}