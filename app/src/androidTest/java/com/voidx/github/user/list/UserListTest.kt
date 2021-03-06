package com.voidx.github.user.list

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import br.com.concretesolutions.requestmatcher.InstrumentedTestRequestMatcherRule
import br.com.concretesolutions.requestmatcher.RequestMatcherRule
import com.voidx.github.feature.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListTest {


    private val mockWebServer  = MockWebServer().apply {
        try {
            start(8080)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    var serverRule: RequestMatcherRule = InstrumentedTestRequestMatcherRule(mockWebServer)

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
            .withSuccessfulResponses()
            .start()
            .checkUsersAreDisplayed()
            .checkEmptyErrorIsNotDisplayed()
            .checkErrorIsNotDisplayed()
    }

    @Test
    fun show_empty_error_successfully() {
        robot
            .withEmptySuccessfulResponses()
            .start()
            .checkEmptyErrorIsDisplayed()
            .checkUsersAreNotDisplayed()
            .checkErrorIsNotDisplayed()
    }

    @Test
    fun show_error_successfully() {
        robot
            .withErrorResponse()
            .start()
            .checkErrorIsDisplayed()
            .checkEmptyErrorIsNotDisplayed()
            .checkUsersAreNotDisplayed()
    }

}