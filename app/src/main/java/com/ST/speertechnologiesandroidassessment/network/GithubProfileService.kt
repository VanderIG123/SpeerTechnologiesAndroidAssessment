package com.ST.speertechnologiesandroidassessment.network

import com.ST.speertechnologiesandroidassessment.GithubProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubProfileService {


    @GET("/users/{username}")
    fun getProfile(@Path("username") username: String): Call<GithubProfile>


    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String):Call<List<GithubProfile>>

}