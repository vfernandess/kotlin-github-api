package com.voidx.github.data.network.vo

import com.squareup.moshi.Json

data class UserVO(
    val login: String,
    @Json(name = "avatar_url")
    val avatar: String,
    val name: String,
    val blog: String,
    val location: String,
    val followers: Int,
    var following: Int
)
