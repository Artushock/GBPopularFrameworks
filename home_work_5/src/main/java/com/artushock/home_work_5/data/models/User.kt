package com.artushock.home_work_5.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "github_users_table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: Int = -1,

    @ColumnInfo(name = "login")
    @SerializedName("login")
    val login: String,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @ColumnInfo(name = "type")
    @SerializedName("type")
    val type: String,

    @ColumnInfo(name = "site_admin")
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
) : Parcelable