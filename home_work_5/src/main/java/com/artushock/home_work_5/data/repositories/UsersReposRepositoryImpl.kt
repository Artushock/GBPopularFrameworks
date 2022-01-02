package com.artushock.home_work_5.data.repositories

import com.artushock.home_work_5.data.models.UserReposData
import com.artushock.home_work_5.data.models.UserReposEntity
import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.artushock.home_work_5.data.room.UserReposDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UsersReposRepositoryImpl
@Inject constructor(
    private val api: GitHubApi,
    private val dao: UserReposDao,
) : UsersReposRepository {

    override fun getUserRepos(login: String): Single<List<UserReposEntity>> {
        return dao.getUserRepos(login)
            .flatMap {
                if (it.isEmpty()) {
                    api.fetchUserRepositories(login)
                        .flatMap { response: List<UserReposData> ->
                            saveDataToDB(response)
                            Single.just(mapDataListToEntityList(response))
                        }
                } else {
                    Single.just(it)
                }
            }
    }

    private fun saveDataToDB(response: List<UserReposData>) {
        for (item in response) {
            dao.saveUserRepo(mapDataToEntity(item))
        }
    }

    private fun mapDataListToEntityList(response: List<UserReposData>): List<UserReposEntity> {
        val resultList = ArrayList<UserReposEntity>()
        for (item in response){
            resultList.add(mapDataToEntity(item))
        }
        return resultList
    }

    private fun mapDataToEntity(repo: UserReposData) = UserReposEntity(
        id = repo.id,
        login = repo.owner.login,
        name = repo.name,
        private = repo.private,
        htmlUrl = repo.htmlUrl,
        description = repo.description,
        createdAt = repo.createdAt,
        updatedAt = repo.updatedAt,
        pushedAt = repo.pushedAt,
        size = repo.size,
        forks = repo.forks,
        watchers = repo.watchers,
    )
}
