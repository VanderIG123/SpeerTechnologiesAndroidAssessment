package com.ST.speertechnologiesandroidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ST.speertechnologiesandroidassessment.databinding.ActivityMainBinding
import com.ST.speertechnologiesandroidassessment.utils.Utils.hide
import com.ST.speertechnologiesandroidassessment.utils.Utils.removeFromParent
import com.ST.speertechnologiesandroidassessment.utils.Utils.show
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val viewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding?.root)
    }


    override fun onResume() {
        super.onResume()
        initPage()
    }

    private fun initPage() {
        mBinding?.searchBtn?.setOnClickListener {
            viewModel.fetchGithubProfile(mBinding?.searchTxt?.text.toString())
        }

        viewModel.githubProfileLiveData.observe(this) {
            handleGithubProfileResult(it)
        }
    }


    private fun handleGithubProfileResult(profileState: ProfileState?) {

        when (profileState) {
            ProfileState.Loading -> mBinding?.progressBar?.show()
            is ProfileState.Loaded -> showGithubProfileDetails(profileState.profile)
            ProfileState.NotFound -> showProfileNotFoundMessage()
            ProfileState.NotLoaded -> {}
            else -> {}
        }
    }

    private fun showGithubProfileDetails(profile: GithubProfile) {
        mBinding?.progressBar?.removeFromParent()
        mBinding?.notingTxt?.hide()
        mBinding?.githubProfileContainer?.show()
        mBinding?.githubProfileCard?.usernameTxt?.text = profile.email
        mBinding?.githubProfileCard?.emailTxt?.text = profile.name
        mBinding?.githubProfileCard?.descriptionTxt?.text = profile.description
        mBinding?.githubProfileCard?.followersCountTxt?.text =
            "${getString(R.string.followers)}: ${profile.followerCount}"
        mBinding?.githubProfileCard?.followingCountTxt?.text =
            "${getString(R.string.following)}: ${profile.followingCount}"


        ////.placeholder(R.drawable.loading_spinner)
        Glide
            .with(this)
            .load(profile.avatarUrl)
            .into(mBinding?.githubProfileCard?.profileImg!!)


        val adapter = ProfileAdapter(profile.followerList)
        mBinding?.followersList?.layoutManager = LinearLayoutManager(this)
        mBinding?.followersList?.adapter = adapter


    }

    private fun showProfileNotFoundMessage() {
        mBinding?.progressBar?.removeFromParent()
        mBinding?.notingTxt?.show()
        mBinding?.githubProfileContainer?.hide()
    }


}