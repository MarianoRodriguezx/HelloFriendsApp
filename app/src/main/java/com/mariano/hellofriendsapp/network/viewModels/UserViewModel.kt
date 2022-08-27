package com.mariano.hellofriendsapp.network.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mariano.hellofriendsapp.network.apiService.ErrorResponse
import com.mariano.hellofriendsapp.network.apiService.Response
import com.mariano.hellofriendsapp.network.apiService.UserServices
import com.mariano.hellofriendsapp.utils.models.DataProfile
import com.mariano.hellofriendsapp.utils.models.TokenClosed
import com.mariano.hellofriendsapp.utils.models.TokenResponse
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

    fun login(email: String, password: String, onComplete: (user: TokenResponse) -> Unit)
    {
        HelloFriendsServices.login(email, password).enqueue(object : Callback<Response<TokenResponse>> {
            override fun onResponse(
                call: Call<Response<TokenResponse>>,
                response: retrofit2.Response<Response<TokenResponse>>
            ) {
                if (response.isSuccessful)
                {
                    response.body()?.data?.let { onComplete(it) }
                }

                else {
                    val e = ErrorResponse.parse(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<Response<TokenResponse>>, t: Throwable) {
                    t.printStackTrace()
            }

        })
    }

    fun logout(onComplete: (user: TokenClosed) -> Unit)
    {
        HelloFriendsServices.logout().enqueue(object : Callback<Response<TokenClosed>> {
            override fun onResponse(
                call: Call<Response<TokenClosed>>,
                response: retrofit2.Response<Response<TokenClosed>>
            ) {
                if (response.isSuccessful)
                {
                    response.body()?.data?.let { onComplete(it) }
                }

                else {
                    val e = ErrorResponse.parse(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<Response<TokenClosed>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun userInfo(onComplete: (user: DataProfile) -> Unit)
    {
        HelloFriendsServices.userInfo().enqueue(object : Callback<Response<DataProfile>> {
            override fun onResponse(
                call: Call<Response<DataProfile>>,
                response: retrofit2.Response<Response<DataProfile>>
            ) {
                if (response.isSuccessful)
                {
                    response.body()?.data?.let { onComplete(it) }
                }

                else {
                    val e = ErrorResponse.parse(response.errorBody()!!.string())
                }
            }

            override fun onFailure(call: Call<Response<DataProfile>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}