package com.ST.speertechnologiesandroidassessment

sealed class ProfileState {
    object NotLoaded : ProfileState()
    object Loading : ProfileState()
    class Loaded(val profile: GithubProfile) : ProfileState()
    object NotFound : ProfileState()
}