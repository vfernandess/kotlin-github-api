package com.voidx.github.feature

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.voidx.github.R
import com.voidx.github.feature.user.detail.view.UserDetailFragment
import com.voidx.github.feature.user.list.view.ListUserFragment
import org.koin.android.ext.android.inject

const val LIST = "USERS_LIST"
const val DETAIL = "DETAIL_LIST"

class MainActivity : AppCompatActivity(), Navigator {

    val listUserFragment: ListUserFragment by inject()

    val userDetailFragment: UserDetailFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showUserList()
    }

    override fun showUserList() {
        supportFragmentManager.findFragmentByTag(LIST).let {
            if (it == null) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, listUserFragment, LIST)
                    .commit()
            }
        }
    }

    override fun showUserDetail(nick: String) {
        userDetailFragment.arguments = UserDetailFragment.withArguments(nick)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.container, userDetailFragment, DETAIL)
            .addToBackStack(null)
            .commit()
    }

}
