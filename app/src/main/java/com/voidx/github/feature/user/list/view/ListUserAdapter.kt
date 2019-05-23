package com.voidx.github.feature.user.list.view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.voidx.github.feature.user.list.UserListContract

class ListUserAdapter(val delegate: UserListContract.Presenter): RecyclerView.Adapter<ListUserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ListUserHolder {
        return ListUserHolder(parent)
    }

    override fun getItemCount(): Int {
        return delegate.getUserCount()
    }

    override fun onBindViewHolder(holder: ListUserHolder, position: Int) {
        delegate.putValues(holder, position)
    }

}