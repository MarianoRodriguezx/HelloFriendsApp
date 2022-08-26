package com.mariano.hellofriendsapp.views.users

import com.mariano.hellofriendsapp.databinding.FragmentDetailContactBinding
import com.mariano.hellofriendsapp.views.BaseFragment
import com.squareup.picasso.Picasso

class DetailContactFragment : BaseFragment<FragmentDetailContactBinding>() {

    lateinit var username: String
    lateinit var image_profile: String
    lateinit var id_user: Number

    override fun setUpViews() {
        setData()
    }

    override fun observeData() {

        val args = DetailContactFragmentArgs.fromBundle(requireArguments())
        username = args.username
        id_user = args.id
        image_profile = args.imageProfile

    }

    private fun setData()
    {
        Picasso.get().load(image_profile).into(binding.profileImg)
        binding.username.text = username
    }

    override val viewBinding: FragmentDetailContactBinding
        get() = FragmentDetailContactBinding.inflate(layoutInflater)
}