package com.voidx.github.user.detail

import com.voidx.github.data.network.USERS_LIST
import com.voidx.github.utils.TestUiUtils
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

const val USER_DETAIL = USERS_LIST + "/johndoe"

class UserDetailUiObjects {

    companion object {

        fun injectsUserDetail(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {

                    val path = request.path.subSequence(1, request.path.length)

                    return when(path) {
                        USERS_LIST -> MockResponse()
                            .setResponseCode(200)
                            .setBody(TestUiUtils.readFixture("github_users_list_200.json"))
                        USER_DETAIL -> MockResponse()
                            .setResponseCode(200)
                            .setBody(TestUiUtils.readFixture("github_user_detail_200.json"))
                        else -> MockResponse()
                            .setResponseCode(404)
                            .setBody(TestUiUtils.readFixture("generic_error.json"))
                    }
                }
            }
        }

        fun injectsError(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val path = request.path.subSequence(1, request.path.length)

                    return when(path) {
                        USERS_LIST -> MockResponse()
                            .setResponseCode(200)
                            .setBody(TestUiUtils.readFixture("github_users_list_200.json"))
                        else -> MockResponse()
                            .setResponseCode(404)
                            .setBody(TestUiUtils.readFixture("generic_error.json"))
                    }
                }
            }
        }

    }

}