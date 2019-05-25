package com.voidx.github.user.detail

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.voidx.github.R
import com.voidx.github.feature.MainActivity
import com.voidx.github.feature.user.list.view.ListUserHolder
import com.voidx.github.user.detail.UserDetailUiObjects.Companion.injectsError
import com.voidx.github.user.detail.UserDetailUiObjects.Companion.injectsUserDetail
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not

class UserDetailRobot(
    private val serverRule: RequestMatcherRule,
    private val activity: ActivityTestRule<MainActivity>
) {

    fun start() = apply {
        activity.launchActivity(Intent())
    }


    fun withSuccessfulResponses() = apply {
        serverRule.mockWebServer.dispatcher = injectsUserDetail()
    }

    fun withErrorResponses() = apply {
        serverRule.mockWebServer.dispatcher = injectsError()
    }

    fun navigateToDetail() = apply {
        onView(withId(R.id.users)).perform(actionOnItemAtPosition<ListUserHolder>(0, click()))
    }

    fun checkUserDetailIsDisplayed() = apply {
        onView(withId(R.id.detailContainer)).check(matches(isDisplayed()))
        onView(withText("John Doe")).check(matches(isDisplayed()))
        onView(allOf(withId(R.id.nick), withText("@johndoe"))).check(matches(isDisplayed()))
    }

    fun checkUserDetailIsNotDisplayed() = apply {
        onView(withId(R.id.detailContainer)).check(matches(not(isDisplayed())))
    }

    fun checkErrorIsDisplayed() = apply {
        onView(withId(R.id.errorDetailContainer)).check(matches(isDisplayed()))
    }

    fun checkErrorIsNotDisplayed() = apply {
        onView(withId(R.id.errorDetailContainer)).check(matches(not(isDisplayed())))
    }
}