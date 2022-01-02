package com.artushock.home_work_5.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_user_repos_table")
data class UserReposEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "login")
    var login: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "private")
    var private: Boolean = false,

    @ColumnInfo(name = "html_url")
    var htmlUrl: String = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "created_at")
    var createdAt: String = "",

    @ColumnInfo(name = "updated_at")
    var updatedAt: String = "",

    @ColumnInfo(name = "pushed_at")
    var pushedAt: String = "",

    @ColumnInfo(name = "size")
    var size: Int = -1,

    @ColumnInfo(name = "forks")
    var forks: Int = -1,

    @ColumnInfo(name = "watchers")
    var watchers: Int = -1,
)