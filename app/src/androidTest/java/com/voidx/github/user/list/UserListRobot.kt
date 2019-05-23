package com.voidx.github.user.list

import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.voidx.github.feature.MainActivity
import android.content.Intent
import com.voidx.github.user.list.UserListUiObjects.Companion.injectsUserList

class UserListRobot(
    private val serverRule: RequestMatcherRule,
    private val activity: ActivityTestRule<MainActivity>) {

    fun start() = apply {
        activity.launchActivity(Intent())
    }

    fun withSuccessfulResponses() = apply {
        serverRule.mockWebServer.dispatcher = injectsUserList()
    }

}