package com.voidx.github.user.list

import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.voidx.github.feature.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserListTest {

    private var mMockWebServer: MockWebServer = MockWebServer().apply {
        this.start(8080)
    }

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    var serverRule: RequestMatcherRule = InstrumentedTestRequestMatcherRule(mMockWebServer)

    lateinit var robot: UserListRobot

    @Before
    fun setUp() {
        robot = UserListRobot(serverRule, activityRule)
    }

    @After
    fun finish() {
        activityRule.finishActivity()
    }

    @Test
    fun show_github_users_successfully() {
        robot
            .start()
            .withSuccessfulResponses()
            .checkUsersAreDisplayed()
            .checkEmptyErrorIsNotDisplayed()
            .checkErrorIsNotDisplayed()
    }

    @Test
    fun show_empty_error_successfully() {
        robot
            .start()
            .withEmptySuccessfulResponses()
            .checkEmptyErrorIsDisplayed()
            .checkUsersAreNotDisplayed()
            .checkErrorIsNotDisplayed()
    }

    @Test
    fun show_error_successfully() {
        robot
            .start()
            .withErrorResponse()
            .checkErrorIsDisplayed()
            .checkEmptyErrorIsNotDisplayed()
            .checkUsersAreNotDisplayed()
    }

}