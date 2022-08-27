package com.mariano.hellofriendsapp.views.users

import androidx.fragment.app.viewModels
import com.mariano.hellofriendsapp.databinding.FragmentContactsBinding
import com.mariano.hellofriendsapp.databinding.FragmentHomeBinding
import com.mariano.hellofriendsapp.network.viewModels.UserViewModel
import com.mariano.hellofriendsapp.utils.models.Contacts
import com.mariano.hellofriendsapp.views.Adapters.ContactsAdapter
import com.mariano.hellofriendsapp.views.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactsFragment : BaseFragment<FragmentContactsBinding>(), ContactsAdapter.OnContactClickListener {

    private val userViewModel by viewModels<UserViewModel>()

    override fun setUpViews() {
        setContacts()
    }

    override fun observeData() {

    }

    private fun setContacts()
    {
        userViewModel.contactos {
            e -> setAdapter(e)
        }
    }

    private fun setAdapter(list: List<Contacts>)
    {
        val adapter = ContactsAdapter(list, this)
        binding.rcvContacts.setHasFixedSize(true)
        binding.rcvContacts.adapter = adapter
    }

    override val viewBinding: FragmentContactsBinding
        get() = FragmentContactsBinding.inflate(layoutInflater)

    override fun onContactClikListener(user: Contacts) {

        val action = ContactsFragmentDirections.contactsToRadio(user.contacto)
        navController.navigate(action)

    }
}