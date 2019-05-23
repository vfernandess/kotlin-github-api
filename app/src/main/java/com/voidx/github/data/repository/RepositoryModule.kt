package com.voidx.github.data.repository

import com.voidx.github.data.repository.impl.UserRepository
import com.voidx.github.data.repository.impl.remote.UserRemoteDataSource
import org.koin.dsl.module

var repositoryModule = module {

    factory<UserDataSource> {
        UserRepository(
            UserRemoteDataSource(get())
        )
    }

}