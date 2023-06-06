package com.ST.speertechnologiesandroidassessment.utils

import android.view.View

object Utils {

    fun View.show() {
        visibility = View.VISIBLE
    }

    fun View.hide() {
        visibility = View.INVISIBLE
    }

    fun View.removeFromParent() {
        visibility = View.GONE
    }
}