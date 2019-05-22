package com.voidx.github.user.list

import com.squareup.moshi.Types
import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.utils.TestUtils
import io.reactivex.Observable


class UserListRobot {

    companion object {

        fun injectUserList(): Observable<List<UserVO>> {
            val list = Types.newParameterizedType(List::class.java, UserVO::class.java)
            val result: List<UserVO>? = TestUtils.getObject("github_users_list_200.json", list)
            return Observable.just(result)
        }

    }

}