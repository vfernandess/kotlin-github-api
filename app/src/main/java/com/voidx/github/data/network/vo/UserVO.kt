package com.voidx.github.data.network.vo

data class UserVO(
    val login: String,
    val avatar: String,
    val name: String,
    val blog: String,
    val location: String,
    val followers: Int,
    var following: Int
)
