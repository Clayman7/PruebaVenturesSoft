package com.cyberapp.pruebaventuressoft.ui.detail.ui.detail.empleados

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cyberapp.pruebaventuressoft.data.BusquedaEmpleadoResponse
import com.cyberapp.pruebaventuressoft.data.LoginResponse
import com.cyberapp.pruebaventuressoft.data.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

@HiltViewModel
class EmpleadosViewModel @Inject constructor(private val retrofitService: RetrofitService): ViewModel() {


    suspend fun getEmpleados(): String? {
        return suspendCancellableCoroutine { continuation ->
            val call = retrofitService.obtenerEmpleados()
            call.enqueue(object : Callback<BusquedaEmpleadoResponse> {
                override fun onResponse(call: Call<BusquedaEmpleadoResponse>, response: Response<BusquedaEmpleadoResponse>) {
                    if (response.isSuccessful) {
                        val empleadoResponse = response.body()
                        continuation.resume(empleadoResponse?.empleados.toString())
                        Log.i("AQuiiiiiii", "${empleadoResponse?.empleados.toString()}")
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<BusquedaEmpleadoResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}