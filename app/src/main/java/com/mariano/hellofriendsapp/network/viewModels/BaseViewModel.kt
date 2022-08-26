package com.mariano.hellofriendsapp.network.viewModels

import androidx.lifecycle.ViewModel
import com.mariano.hellofriendsapp.App

open class BaseViewModel<ModelClass> internal constructor(
    val services: ModelClass
) : ViewModel(){
    val TAG = "okHttp"
    protected val app = App.getInstance()

    companion object {
        const val serverError = "Error del servidor"
        const val imageError = "Error al cargar imagen"
    }
}