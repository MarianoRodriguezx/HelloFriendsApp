package com.mariano.hellofriendsapp.network.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mariano.hellofriendsapp.network.apiService.ErrorResponse
import com.mariano.hellofriendsapp.network.apiService.Response
import com.mariano.hellofriendsapp.network.apiService.UserServices
import com.mariano.hellofriendsapp.utils.models.searchUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import javax.inject.Inject
import retrofit2.Callback

@HiltViewModel
class UserViewModel @Inject constructor(private val HelloFriendsServices: UserServices):BaseViewModel<UserServices>(HelloFriendsServices) {

    private val UsersLiveData = MutableLiveData<List<searchUsers>>()
    val UsersData : LiveData<List<searchUsers>> get() = UsersLiveData

    fun searchUser(dt: String, onComplete: (users: List<searchUsers>) -> Unit)
    {
        HelloFriendsServices.searchView(dt).enqueue(object : Callback<Response<List<searchUsers>>> {
            override fun onResponse(
                call: Call<Response<List<searchUsers>>>,
                response: retrofit2.Response<Response<List<searchUsers>>>
            ) {
                if (response.isSuccessful)
                {
                    onComplete(response.body()?.data?: emptyList())
                }

                else {
                    val e = ErrorResponse.parse(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<Response<List<searchUsers>>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}