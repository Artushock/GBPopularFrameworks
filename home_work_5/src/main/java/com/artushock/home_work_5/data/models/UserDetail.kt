package com.artushock.home_work_5.data.models

data class UserDetail(
    val id: Int,
    val login: String,
    val url: String,
    val type: String,
    val sysAdmin: Boolean,
)
