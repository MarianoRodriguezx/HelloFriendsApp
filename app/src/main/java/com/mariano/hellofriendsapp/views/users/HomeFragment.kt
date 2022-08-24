package com.mariano.hellofriendsapp.views.users

import com.mariano.hellofriendsapp.databinding.FragmentHomeBinding
import com.mariano.hellofriendsapp.views.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    override fun setUpViews() {

    }

    override fun observeData() {

    }

    override val viewBinding: FragmentHomeBinding
        get() = FragmentHomeBinding.inflate(layoutInflater)
}