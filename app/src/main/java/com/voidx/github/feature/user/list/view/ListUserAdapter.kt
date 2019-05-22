package com.voidx.github.feature.user.list.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.voidx.github.feature.user.list.UserListContract

class ListUserAdapter(val delegate: ListUserDelegate): RecyclerView.Adapter<ListUserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ListUserHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return delegate.getUserCount()
    }

    override fun onBindViewHolder(holder: ListUserHolder, position: Int) {
        delegate.putValues(holder, position)
    }

    interface ListUserDelegate {

        fun getUserCount(): Int

        fun putValues(view: UserListContract.ItemView, position: Int)

    }

}