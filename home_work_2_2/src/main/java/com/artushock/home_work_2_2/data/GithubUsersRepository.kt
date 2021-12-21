package com.artushock.home_work_2_2.data

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepository {

    private val repository = mutableListOf<GithubUser>(
        GithubUser("Andrew", "11111111"),
        GithubUser("Tom", "22222222"),
        GithubUser("Jack", "33333333"),
        GithubUser("Boris", "44444444"),
        GithubUser("Jerry", "55555555"),
        GithubUser("Peter", "66666666"),
        GithubUser("Roy", "77777777"),
    )

    fun getUsers(): Observable<GithubUser> {
        return Observable.fromIterable(repository)
    }

    fun getUserByName(login: String): Observable<GithubUser> {
        return Observable.fromIterable(repository)
            .distinct()
            .filter {
                it.login == login
            }
    }

    fun addUser(user: GithubUser){
        repository.add(user)
    }

}