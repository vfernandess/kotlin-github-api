package com.voidx.github.feature.user.list

import com.voidx.github.feature.user.list.business.UserListPresenter
import com.voidx.github.feature.user.list.view.ListUserAdapter
import org.koin.dsl.module

val userListModule = module {

    factory<UserListContract.Presenter> { (view: UserListContract.View) ->
        UserListPresenter(view, get())
    }

    factory {
        ListUserAdapter(get())
    }

}
