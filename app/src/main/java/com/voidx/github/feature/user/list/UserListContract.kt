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

//        fun hideError()
    }

    interface Presenter {

        fun load()

        fun onItemSelected(position: Int)

        fun destroy()
    }

}