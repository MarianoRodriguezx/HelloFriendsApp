package com.mariano.hellofriendsapp.views.users

import com.mariano.hellofriendsapp.databinding.FragmentProfileBinding
import com.mariano.hellofriendsapp.views.BaseFragment

class ProfileFragment:BaseFragment<FragmentProfileBinding>() {
    override fun setUpViews() {

    }

    override fun observeData() {

    }

    override val viewBinding: FragmentProfileBinding
        get() = FragmentProfileBinding.inflate(layoutInflater)
}