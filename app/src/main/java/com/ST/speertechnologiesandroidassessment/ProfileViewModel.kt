package com.ST.speertechnologiesandroidassessment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ST.speertechnologiesandroidassessment.repositories.GithubProfilesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val profileRepo: GithubProfilesRepo) : ViewModel() {

    private val _githubProfileLiveData = MutableLiveData<ProfileState>(ProfileState.NotLoaded)
    val githubProfileLiveData: LiveData<ProfileState> = _githubProfileLiveData


    fun fetchGithubProfile(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _githubProfileLiveData.value = ProfileState.Loading
            }
            val profile = profileRepo.fetchProfile(username)
            withContext(Dispatchers.Main) {
                _githubProfileLiveData.value = getUpdatedState(profile)
            }
        }
    }


    private fun getUpdatedState(githubProfile: GithubProfile?): ProfileState {
        return if (githubProfile != null) {
            ProfileState.Loaded(githubProfile)
        } else {
            ProfileState.NotFound
        }
    }
}