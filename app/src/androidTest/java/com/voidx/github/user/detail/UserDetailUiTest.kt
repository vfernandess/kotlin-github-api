package com.voidx.github.user.detail

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
class UserDetailUiTest {

    private val mockWebServer  = MockWebServer().apply {
        try {
            start(8080)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Rule
    @JvmField
    val serverRule: RequestMatcherRule = InstrumentedTestRequestMatcherRule(mockWebServer)

    lateinit var robot: UserDetailRobot

    @Before
    fun setUp() {
        robot = UserDetailRobot(serverRule, activityRule)
    }

    @After
    fun finish() {
        activityRule.finishActivity()
    }

    @Test
    fun show_github_user_detail_successfully() {
        robot
            .withSuccessfulResponses()
            .start()
            .navigateToDetail()
            .checkUserDetailIsDisplayed()
            .checkErrorIsNotDisplayed()
    }

    @Test
    fun show_github_user_detail_error_successfully() {
        robot
            .withErrorResponses()
            .start()
            .navigateToDetail()
            .checkUserDetailIsNotDisplayed()
            .checkErrorIsDisplayed()
    }

}