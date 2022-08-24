package com.mariano.hellofriendsapp.views.users

import com.mariano.hellofriendsapp.databinding.FragmentContactsBinding
import com.mariano.hellofriendsapp.databinding.FragmentHomeBinding
import com.mariano.hellofriendsapp.views.BaseFragment

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun setUpViews() {

    }

    override fun observeData() {

    }

    override val viewBinding: FragmentContactsBinding
        get() = FragmentContactsBinding.inflate(layoutInflater)
}