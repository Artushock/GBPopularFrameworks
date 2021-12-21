package com.artushock.home_work_5.data

object GitHubUsersRepositoryFactory {
    fun create(): GitHubUserRepository = GitHubUsersRepositoryImpl()
}