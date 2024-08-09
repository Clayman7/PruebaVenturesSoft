package com.cyberapp.pruebaventuressoft.data


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import okhttp3.OkHttpClient



class RetrofitService @Inject constructor() {

    val client = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor( Constantes.token))
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Constantes.URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val apiService = getRetrofit().create(ApiService::class.java)


    fun getApi(email: String, password: String): Call<LoginResponse> {
        // Realizar la solicitud
        val call = apiService.login(email, password)
        return call
    }

    val request = BusquedaEmpleadoRequest(
        filtroEmpleado = "",
        idCia = 10046,
        idUsuario = 357,
        numRegistros = 500,
        pagina = 1)


    // Función para obtener empleados
    fun obtenerEmpleados(): Call<BusquedaEmpleadoResponse> {
       val call = apiService.obtenerEmpleados(request)
        return call
    }

}






