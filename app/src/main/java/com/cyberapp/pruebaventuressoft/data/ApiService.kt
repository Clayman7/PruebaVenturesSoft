package com.cyberapp.pruebaventuressoft.data


import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header

interface ApiService {
    @FormUrlEncoded
    @POST("HumaneTime/api/Authorization/AccesoUsuario")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>


    @POST("BusquedaEmpleado")
    fun obtenerEmpleados( @Body request: BusquedaEmpleadoRequest): Call<BusquedaEmpleadoResponse>
}
