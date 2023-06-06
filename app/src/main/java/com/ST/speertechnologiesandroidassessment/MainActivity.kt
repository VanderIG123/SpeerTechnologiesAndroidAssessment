package com.ST.speertechnologiesandroidassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.ST.speertechnologiesandroidassessment.databinding.ActivityMainBinding


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
            beginSearch(mBinding?.searchTxt?.text.toString())
        }
    }

    private fun beginSearch(searchKeyword: String) {
        val profileFragment = ProfileFragment()
        profileFragment.searchKeyword = searchKeyword


        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.fragment_container, profileFragment)
            .commit()
    }

}