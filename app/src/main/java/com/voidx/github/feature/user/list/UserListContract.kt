package com.voidx.github.feature.user.list

interface UserListContract {

    interface ItemView {

        fun putValues(name: String, photo: String, nick: String)
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

        fun putValues(view: UserListContract.ItemView, position: Int)

        fun destroy()
    }

}