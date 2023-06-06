package com.ST.speertechnologiesandroidassessment

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ST.speertechnologiesandroidassessment.databinding.FragmentProfileBinding
import com.ST.speertechnologiesandroidassessment.utils.Utils.hide
import com.ST.speertechnologiesandroidassessment.utils.Utils.removeFromParent
import com.ST.speertechnologiesandroidassessment.utils.Utils.show
import com.bumptech.glide.Glide


class ProfileFragment : Fragment() {

    private val viewModel by viewModels<MainActivityViewModel>()
    private var mBinding: FragmentProfileBinding? = null
    var searchKeyword = ""
    var profileTapListener: ProfileTapListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentProfileBinding.inflate(layoutInflater)
        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPage()
    }

    private fun initPage() {
        viewModel.githubProfileLiveData.observe(viewLifecycleOwner) {
            handleGithubProfileResult(it)
        }

        viewModel.fetchGithubProfile(searchKeyword)

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


        Glide
            .with(this)
            .load(profile.avatarUrl)
            .into(mBinding?.githubProfileCard?.profileImg!!)


        val adapter = ProfileAdapter(profile.followerList, profileTapListener)
        mBinding?.followersList?.layoutManager = LinearLayoutManager(requireContext())
        mBinding?.followersList?.adapter = adapter
    }

    private fun showProfileNotFoundMessage() {
        mBinding?.progressBar?.removeFromParent()
        mBinding?.notingTxt?.show()
        mBinding?.githubProfileContainer?.hide()
    }
}