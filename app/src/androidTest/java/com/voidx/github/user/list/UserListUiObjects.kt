package com.voidx.github.user.list

import com.voidx.github.data.network.USERS_LIST
import com.voidx.github.utils.TestUiUtils.Companion.readFixture
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class UserListUiObjects {

    companion object {

        fun injectsUserList(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val pathIndex = request.path.indexOf("?")
                    val path = if (pathIndex >= 0) request.path.substring(0, pathIndex) else request.path
                    return when (path) {
                        USERS_LIST -> MockResponse()
                            .setResponseCode(200)
                            .setBody(readFixture("github_users_list_200.json"))

                        else -> MockResponse().setResponseCode(404).setBody(path)
                    }
                }
            }
        }

        fun injectsEmptyUserList(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    val pathIndex = request.path.indexOf("?")
                    val path = if (pathIndex >= 0) request.path.substring(0, pathIndex) else request.path
                    return when (path) {
                        USERS_LIST -> MockResponse()
                            .setResponseCode(200)
                            .setBody(readFixture("github_users_list_empty_200.json"))

                        else -> MockResponse().setResponseCode(404).setBody(path)
                    }
                }
            }
        }

        fun injectsError(): Dispatcher {
            return object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return MockResponse().setResponseCode(404).setBody(readFixture("generic_error.json"))
                }
            }
        }

    }

}