package com.mariano.hellofriendsapp.views.users

import android.util.Log
import androidx.fragment.app.viewModels
import com.mariano.hellofriendsapp.App
import com.mariano.hellofriendsapp.databinding.FragmentProfileBinding
import com.mariano.hellofriendsapp.network.viewModels.UserViewModel
import com.mariano.hellofriendsapp.views.BaseFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment:BaseFragment<FragmentProfileBinding>() {

    private val userViewModel by viewModels<UserViewModel>()

    override fun setUpViews() {

        setViews()
        binding.btnLogout.setOnClickListener {
            logout()
        }
    }

    private fun setViews()
    {
        userViewModel.userInfo {
            e -> Picasso.get().load(e.image_profile).into(binding.profileImg)
            binding.email.setText(e.email)
            binding.username.setText(e.username)
            binding.description.setText(e.description)
        }
    }

    private fun logout()
    {
        userViewModel.logout {
            e -> Log.d("revoked", "logout: " + e.revoked)

            val app = App.getInstance()
            app.getSharedPrefs().edit().clear().apply()

            val action = ProfileFragmentDirections.profileFragmentToLogin()
            navController.navigate(action)
        }
    }

    override fun observeData() {

    }

    override val viewBinding: FragmentProfileBinding
        get() = FragmentProfileBinding.inflate(layoutInflater)
}