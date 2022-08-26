package com.mariano.hellofriendsapp.views.users

import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mariano.hellofriendsapp.NavAppDirections
import com.mariano.hellofriendsapp.databinding.FragmentSearchBinding
import com.mariano.hellofriendsapp.network.viewModels.UserViewModel
import com.mariano.hellofriendsapp.utils.models.searchUsers
import com.mariano.hellofriendsapp.views.Adapters.SearchUserAdapter
import com.mariano.hellofriendsapp.views.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment:BaseFragment<FragmentSearchBinding>(), SearchView.OnQueryTextListener, SearchUserAdapter.OnUserClickListener {
    private val userViewModel by viewModels<UserViewModel>()

    override fun setUpViews() {
        binding.srcSearch.setOnQueryTextListener(this)
    }

    override fun observeData() {}

    private fun setSearch(list: List<searchUsers>)
    {
        if (list.isEmpty())
        {
            binding.rvcSearch.visibility = View.GONE
            binding.imgNothing.visibility = View.VISIBLE
            binding.txtNothing.visibility = View.VISIBLE
        }
        else
        {
            binding.rvcSearch.visibility = View.VISIBLE
            binding.imgNothing.visibility = View.GONE
            binding.txtNothing.visibility = View.GONE

            val adapter = SearchUserAdapter(list, this)
            binding.rvcSearch.setHasFixedSize(true)
            binding.rvcSearch.adapter = adapter
        }
    }

    override val viewBinding: FragmentSearchBinding
        get() = FragmentSearchBinding.inflate(layoutInflater)

    override fun onQueryTextSubmit(p0: String?): Boolean {
        userViewModel.searchUser(p0.toString()){
            e ->
            setSearch(e)
        }
        return false;
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onUserClick(user: searchUsers) {
        val action = SearchFragmentDirections.searchToDetail(user.id, user.username, user.image_profile)
        navController.navigate(action)
        //Toast.makeText(this.context, user.id.toString(), Toast.LENGTH_SHORT).show()
    }
}