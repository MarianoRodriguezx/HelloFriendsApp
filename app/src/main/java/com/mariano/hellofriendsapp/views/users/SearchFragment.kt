package com.mariano.hellofriendsapp.views.users

import com.mariano.hellofriendsapp.databinding.FragmentSearchBinding
import com.mariano.hellofriendsapp.views.BaseFragment

class SearchFragment:BaseFragment<FragmentSearchBinding>() {
    override fun setUpViews() {

    }

    override fun observeData() {

    }

    override val viewBinding: FragmentSearchBinding
        get() = FragmentSearchBinding.inflate(layoutInflater)
}