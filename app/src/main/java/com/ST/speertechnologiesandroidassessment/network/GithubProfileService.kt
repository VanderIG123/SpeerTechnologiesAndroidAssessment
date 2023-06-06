package com.ST.speertechnologiesandroidassessment.network

import com.ST.speertechnologiesandroidassessment.GithubProfile
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubProfileService {

    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer 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",
        "X-GitHub-Api-Version: 2022-11-28"
    )
    @GET("/users/{username}")
    fun getProfile(@Path("username") username: String): Call<GithubProfile>

    @Headers(
        "Accept: application/vnd.github+json",
        "Authorization: Bearer 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",
        "X-GitHub-Api-Version: 2022-11-28"
    )
    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String):Call<List<GithubProfile>>

}