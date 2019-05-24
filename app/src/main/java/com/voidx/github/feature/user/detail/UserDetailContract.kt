package com.voidx.github.feature.user.detail

interface UserDetailContract {

    interface View {

        fun showLoading()

        fun hideLoading()

        fun showDetails()

        fun hideDetails()

        fun showError()

        fun hideError()

        fun showDevInfos(followers: Int, following: Int, blog: String?)

        fun showPersonInfos(name: String, location: String?)
    }

    interface Presenter {

        fun load(user: String)
    }

}