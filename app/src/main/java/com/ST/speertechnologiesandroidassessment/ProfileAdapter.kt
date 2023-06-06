package com.ST.speertechnologiesandroidassessment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfileAdapter(
    val followers: List<GithubProfile>,
    val profileTapListener: ProfileTapListener? = null
) :
    RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = followers[position]
        holder.bind(profile, profileTapListener)
    }


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val profileImg = view.findViewById<ImageView>(R.id.profile_img)
        val userName = view.findViewById<TextView>(R.id.username_Txt)
        val email = view.findViewById<TextView>(R.id.email_txt)

        fun bind(profile: GithubProfile, profileTapListener: ProfileTapListener? = null) {
            Glide
                .with(view.context)
                .load(profile.avatarUrl)
                .into(profileImg)
            userName.text = profile.login
            email.text = profile.email

            view.setOnClickListener {
                profileTapListener?.onTap(profile.login)
            }
        }
    }

}