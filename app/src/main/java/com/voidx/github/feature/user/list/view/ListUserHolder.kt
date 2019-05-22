package com.voidx.github.feature.user.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.voidx.github.feature.user.list.UserListContract

class ListUserHolder(itemView: View) : RecyclerView.ViewHolder(itemView), UserListContract.ItemView {

    init {
    }

    override fun putValues(name: String, photo: String, nick: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}