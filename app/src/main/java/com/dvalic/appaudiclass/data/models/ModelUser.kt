package com.dvalic.appaudiclass.data.models

data class ModelUser(
    val Ok: String? = "",
    val Mensaje: String? = "",
    val Objeto: GetIniciarSesion
)

data class GetIniciarSesion(
    val IdCuenta: String? = "",
    val IdPersona: String? = "",
    val Nombre: String? = "",
    val ApellidoPaterno: String? = "",
    val ApellidoMaterno: String? = "",
    val Correo: String? = "",
    val Clave: String? = "",
    val Token: String? = "",
    val LadaMovil: String? = "",
    val TelefonoMovil: String? = ""
)