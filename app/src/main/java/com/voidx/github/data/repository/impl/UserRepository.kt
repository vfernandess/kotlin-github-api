package com.voidx.github.data.repository.impl

import com.voidx.github.data.network.vo.UserVO
import com.voidx.github.data.repository.UserDataSource
import io.reactivex.Observable

class UserRepository(val remoteDataSource: UserDataSource): UserDataSource {

    override fun getUser(username: String): Observable<UserVO> {
        return remoteDataSource.getUser(username)
    }

    override fun getUsers(): Observable<List<UserVO>> {
        return remoteDataSource.getUsers()
    }
}