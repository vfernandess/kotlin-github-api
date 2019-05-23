package com.voidx.github.feature.user.list

interface UserListContract {

    interface ItemView {

        fun putValues(photo: String, nick: String)
    }

    interface View {

        fun showLoading()

        fun hideLoading()

        fun showUsers()

        fun hideUsers()

        fun showUser(name: String)

        fun showError()

        fun hideError()

        fun showEmptyError()

        fun hideEmpty()
    }

    interface Presenter {

        fun load()

        fun onItemSelected(position: Int)

        fun getUserCount(): Int

        fun putValues(view: ItemView, position: Int)

        fun destroy()
    }

}