package com.artushock.home_work_5.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserReposData(

    @SerializedName("id")
    val id: Int = -1,

    @SerializedName("name")
    val name: String = "",

    @SerializedName("private")
    val private: Boolean,

    @SerializedName("owner")
    val owner: Owner = Owner(),

    @SerializedName("html_url")
    val htmlUrl: String = "",

    @SerializedName("description")
    val description: String? = "",

    @SerializedName("created_at")
    val createdAt: String = "",

    @SerializedName("updated_at")
    val updatedAt: String = "",

    @SerializedName("pushed_at")
    val pushedAt: String = "",

    @SerializedName("size")
    val size: Int = -1,

    @SerializedName("forks")
    val forks: Int = -1,

    @SerializedName("watchers")
    val watchers: Int = -1,
) : Parcelable

@Parcelize
data class Owner(

    @SerializedName("login")
    val login: String = "undefined login",

    @SerializedName("id")
    val id: Int = -1,

    @SerializedName("html_url")
    val htmlUrl: String = ""
) : Parcelable