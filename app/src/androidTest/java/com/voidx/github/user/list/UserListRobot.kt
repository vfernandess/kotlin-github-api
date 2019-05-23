package com.voidx.github.user.list

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.voidx.github.R
import com.voidx.github.feature.MainActivity
import com.voidx.github.user.list.UserListUiObjects.Companion.injectsEmptyUserList
import com.voidx.github.user.list.UserListUiObjects.Companion.injectsError
import com.voidx.github.user.list.UserListUiObjects.Companion.injectsUserList
import org.hamcrest.Matchers.not

class UserListRobot(
    private val serverRule: RequestMatcherRule,
    private val activity: ActivityTestRule<MainActivity>
) {

    fun start() = apply {
        activity.launchActivity(Intent())
    }

    fun withSuccessfulResponses() = apply {
        serverRule.mockWebServer.dispatcher = injectsUserList()
    }

    fun withEmptySuccessfulResponses() = apply {
        serverRule.mockWebServer.dispatcher = injectsEmptyUserList()
    }

    fun withErrorResponse() = apply {
        serverRule.mockWebServer.dispatcher = injectsError()
    }

    fun checkUsersAreDisplayed() = apply {
        onView(withId(R.id.users)).check(matches(isDisplayed()))
        onView(withText("@johndoe")).check(matches(isDisplayed()))
    }

    fun checkUsersAreNotDisplayed() = apply {
        onView(withId(R.id.users)).check(matches(not(isDisplayed())))
    }

    fun checkEmptyErrorIsDisplayed() = apply {
        onView(withId(R.id.emptyContainer)).check(matches(isDisplayed()))
    }

    fun checkEmptyErrorIsNotDisplayed() = apply {
        onView(withId(R.id.emptyContainer)).check(matches(not(isDisplayed())))
    }

    fun checkErrorIsDisplayed() = apply {
        onView(withId(R.id.errorContainer)).check(matches(isDisplayed()))
    }

    fun checkErrorIsNotDisplayed() = apply {
        onView(withId(R.id.errorContainer)).check(matches(not(isDisplayed())))
    }

}