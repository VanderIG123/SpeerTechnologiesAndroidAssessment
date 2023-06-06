package com.ST.speertechnologiesandroidassessment

import com.google.gson.annotations.SerializedName

data class GithubProfile(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val username: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("login")
    val login:String,
    @SerializedName("email")
    val email: String,
    @SerializedName("bio")
    val description: String,
    val followerCount: Int,
    val followingCount: Int,
    @SerializedName("followers_url")
    val followersUrl: String,
    var followerList: List<GithubProfile>
) {
    fun setFollowers(followerList: List<GithubProfile>) {
        this.followerList = followerList
    }
}