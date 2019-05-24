package com.voidx.github.feature

import com.voidx.github.feature.user.detail.userDetailModule
import com.voidx.github.feature.user.list.userListModule

var featureModule =
    listOf(
        userListModule,
        userDetailModule
    )