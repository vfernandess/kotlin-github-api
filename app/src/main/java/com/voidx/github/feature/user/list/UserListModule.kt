package com.voidx.github.feature.user.list

import com.voidx.github.feature.user.list.business.UserListPresenter
import com.voidx.github.feature.user.list.view.ListUserAdapter
import com.voidx.github.feature.user.list.view.ListUserFragment
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val userListModule = module {

    scope(named<ListUserFragment>()) {
        scoped {
            UserListPresenter(get(), get())
        } bind UserListContract.Presenter::class

        scoped {
            ListUserAdapter(get())
        }

    }

    single {
        ListUserFragment()
    } bind UserListContract.View::class

}
