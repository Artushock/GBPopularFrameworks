package com.artushock.geekbrainspopularframeworks.model

class GithubUserRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }

    fun getUserByIndex(index: Int) = repositories[index]
}