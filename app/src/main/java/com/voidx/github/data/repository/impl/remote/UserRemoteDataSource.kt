package com.voidx.github.data.repository.impl.remote

import com.voidx.github.data.network.API
import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import io.reactivex.Observable

class UserRemoteDataSource(val api: API): UserDataSource {

    override fun getUsers(): Observable<List<UserVO>> {
        return api.getUsers()
    }

    override fun getUser(username: String): Observable<UserVO> {
        return api.getUser(username)
    }
}