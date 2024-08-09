package com.cyberapp.pruebaventuressoft.ui.login

import androidx.lifecycle.ViewModel
import com.cyberapp.pruebaventuressoft.data.LoginResponse
import com.cyberapp.pruebaventuressoft.data.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import retrofit2.Call
import javax.inject.Inject
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class MainViewModel @Inject constructor(private val retrofitService: RetrofitService): ViewModel() {

   /* fun getApi(email: String, password: String){
        val call = retrofitService.getApi(email, password)
        call.enqueue(object : retrofit2.Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    println("Login successful: ${loginResponse?.codigo}")
                } else {
                    println("Login failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    } */


    suspend fun getApi(email: String, password: String): String? {
        return suspendCancellableCoroutine { continuation ->
            val call = retrofitService.getApi(email, password)
            call.enqueue(object : retrofit2.Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        continuation.resume(loginResponse?.codigo)
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}