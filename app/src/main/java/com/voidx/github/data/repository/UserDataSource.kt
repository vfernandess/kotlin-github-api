package com.voidx.github.data.repository

import com.voidx.github.data.network.vo.UserVO
import io.reactivex.Observable

interface UserDataSource {

    fun getUsers(): Observable<List<UserVO>>

    fun getUser(username: String): Observable<UserVO>

}