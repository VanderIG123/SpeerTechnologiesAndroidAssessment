package com.ST.speertechnologiesandroidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ST.speertechnologiesandroidassessment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
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
            beginSearch(mBinding?.searchTxt?.text.toString())
        }
    }

    private fun beginSearch(searchKeyword: String) {
        val profileFragment = ProfileFragment()
        profileFragment.searchKeyword = searchKeyword
        profileFragment.profileTapListener = object : ProfileTapListener {
            override fun onTap(profileLogin: String) {
                beginSearch(profileLogin)
            }
        }


        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, profileFragment)
            .commit()
    }

}