package com.voidx.github.feature.user.detail

interface UserDetailContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun showDetails()

        fun hideDetails()

        fun showError()

        fun hideError()

        fun showDevInfo(followers: String, following: String, repoCount: String, gistCount: String)

        fun showPersonInfo(name: String, nick: String, avatar: String)

        fun showAvatar(avatar: String)
    }

    interface Presenter {

        fun load(user: String)

        fun requestAvatar()

    }

}