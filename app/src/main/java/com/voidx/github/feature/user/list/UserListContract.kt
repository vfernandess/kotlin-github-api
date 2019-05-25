package com.voidx.github.feature.user.list

interface UserListContract {

    interface ItemView {

        fun showNick(nick: String)

        fun showAvatar(avatar: String)

        fun showEmptyAvatar()
    }

    interface View {

        fun showLoading()

        fun hideLoading()

        fun showUsers()

        fun hideUsers()

        fun showUser(nick: String)

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