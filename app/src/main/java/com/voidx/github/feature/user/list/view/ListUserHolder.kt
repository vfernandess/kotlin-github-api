package com.voidx.github.feature.user.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.voidx.github.R
import com.voidx.github.feature.user.list.UserListContract
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_list_item.*

class ListUserHolder(override val containerView: View) :
    RecyclerView.ViewHolder(containerView),
    UserListContract.ItemView,
    LayoutContainer {

    override fun showNick(nick: String) {
        title.text = containerView.context.getString(R.string.nick_formatter, nick)
    }

    override fun showAvatar(avatar: String) {
        Glide
            .with(this.avatar)
            .load(avatar)
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(this.avatar)
    }

    override fun showEmptyAvatar() {
        this.avatar.setImageResource(R.drawable.ic_profile)
    }
}