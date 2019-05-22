package com.voidx.github.data.network

import com.voidx.github.data.network.vo.UserVO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface API {

    @GET("users")
    fun getUsers(): Observable<List<UserVO>>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Observable<UserVO>

}