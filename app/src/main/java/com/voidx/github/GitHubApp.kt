package com.voidx.github

import android.app.Application
import com.voidx.github.data.network.networkModule
import com.voidx.github.data.repository.repositoryModule
import com.voidx.github.feature.featureModule
import org.koin.core.context.startKoin

class GitHubApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            modules(
                networkModule +
                        repositoryModule +
                        featureModule
            )
        }

    }

}