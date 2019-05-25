package com.voidx.github.user.detail

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.utils.TestUtils
import io.reactivex.Observable

class UserDetailObjects {

    companion object {

        fun injectUser(): Observable<UserVO> {
            val result: UserVO? = TestUtils.getObject("github_user_detail_200.json", UserVO::class.java)
            return Observable.just(result)
        }

        fun injectUserWithoutAvatar(): Observable<UserVO> {
            var result: UserVO? = TestUtils.getObject("github_user_detail_200.json", UserVO::class.java)
            result?.avatar = null
            return Observable.just(result)
        }

        fun injectError(): Observable<UserVO> {
            return TestUtils.createConnectionErrorObservable()
        }

    }

}