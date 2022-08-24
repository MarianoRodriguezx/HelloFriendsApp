package com.mariano.hellofriendsapp.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.mariano.hellofriendsapp.App
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

abstract class BaseFragment<BindingClass : ViewBinding> : Fragment() {

    protected lateinit var binding: BindingClass
    protected lateinit var navController: NavController
    protected lateinit var viewModelProvider: ViewModelProvider

    private lateinit var app: App

    @Inject
    @ApplicationContext protected lateinit var ctx: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding
        viewModelProvider = ViewModelProvider(requireActivity())
        app = App.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        setUpOnce()
    }

    override fun onResume() {
        super.onResume()
        observeData()
        setUpViews()
    }

    protected open fun setUpOnce() {}
    protected abstract fun setUpViews()
    protected abstract fun observeData()

    protected fun disenableViews(views: Array<View>, enable: Boolean) {
        for (v in views) {
            v.isEnabled = enable
        }
    }

    protected fun viewsVisibility(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    protected fun viewsVisibility(views: Array<View>, isVisible: Boolean) {
        for (v in views) {
            if (isVisible) {
                v.visibility = View.VISIBLE
            } else {
                v.visibility = View.GONE
            }
        }
    }

    protected infix fun NavController.navigateTo (action: NavDirections) {
        navigate(action)
    }

    protected abstract val viewBinding: BindingClass
}