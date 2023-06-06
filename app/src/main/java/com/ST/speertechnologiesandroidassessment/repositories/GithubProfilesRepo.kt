package com.ST.speertechnologiesandroidassessment.repositories

import com.ST.speertechnologiesandroidassessment.GithubProfile
import com.ST.speertechnologiesandroidassessment.network.GithubProfileService
import com.ST.speertechnologiesandroidassessment.network.RetrofitService
import retrofit2.Retrofit
import javax.inject.Inject

class GithubProfilesRepo @Inject constructor(val retrofit: Retrofit) {


    suspend fun fetchProfile(username: String): GithubProfile? {

        val profileService = retrofit.create(GithubProfileService::class.java)
        val profileResponse = profileService.getProfile(username).execute()
        val followersResponse = profileService.getFollowers(username).execute()

        val profile = profileResponse.body()

        followersResponse.body()?.let {
            profile?.setFollowers(it)
        }


        return profile
    }
}