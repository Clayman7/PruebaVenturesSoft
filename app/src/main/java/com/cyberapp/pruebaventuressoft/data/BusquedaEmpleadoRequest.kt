package com.cyberapp.pruebaventuressoft.data

data class BusquedaEmpleadoRequest(
    val filtroEmpleado: String,
    val idCia: Int,
    val idUsuario: Int,
    val numRegistros: Int,
    val pagina: Int
)