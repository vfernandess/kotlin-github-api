package com.voidx.github.feature

interface Navigator {

    fun showUserList()

    fun showUserDetail(nick: String)

    fun showAvatar(avatar: String)

    fun onBackPressed()

}