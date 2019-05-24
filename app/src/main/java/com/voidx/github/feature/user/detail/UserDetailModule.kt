package com.voidx.github.feature.user.detail

import com.voidx.github.feature.user.detail.business.UserDetailPresenter
import com.voidx.github.feature.user.detail.view.UserDetailFragment
import com.voidx.github.feature.user.list.view.ListUserAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

var userDetailModule = module {

    scope(named<UserDetailFragment>()) {

        scoped {
            UserDetailPresenter(get(), get())
        } bind UserDetailContract.Presenter::class
    }

    single {
        UserDetailFragment()
    } bind UserDetailContract.View::class

}