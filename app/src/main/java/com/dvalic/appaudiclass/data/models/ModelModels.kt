package com.dvalic.appaudiclass.data.models

import java.io.Serializable
import java.util.ArrayList

data class ModelModels(
    val IdMarca: String? = "",
    val Modelos: ArrayList<Modelos>? = arrayListOf()
)

data class Modelos(
    val IdModelo: String? = "",
    val NombreModelo: String? = "",
    val Anios: ArrayList<Anios>? = arrayListOf()
)

data class Anios(
    val Anio: String? = "",
    val Atributo1: String? = "",
    val Atributo2: String? = "",
    val Atributo3: String? = "",
    val Atributo4: String? = "",
    val Versiones: ArrayList<Versiones>? = arrayListOf(),
    val GamaColores: ArrayList<GamaColores>? = arrayListOf(),
    val Atributo5: String? = "",
    val Atributo6: String? = ""
)

data class Versiones(
    val Nombre: String? = "",
    val RutaVideo: String? = "",
    val RutaFichaTecnica: String? = "",
    val Ruta360: String? = "",
    val PrecioTransmision: ArrayList<PrecioTransmision>? = arrayListOf(),
    val Colores: ArrayList<Colores>? = arrayListOf(),
    val Accesorios: ArrayList<AccesoriosModelos>? = arrayListOf()
) : Serializable

data class PrecioTransmision(
    val IdVersion: String? = "",
    val Transmision: String? = "",
    val PrecioContado: String? = "",
    val PrecioLista: String? = "",
    val Iva: String? = "",
    val SubTotal: String? = "",
    val Total: String? = ""
)

data class Colores(
    val IdColor: String? = "",
    val Nombre: String? = "",
    val Ruta: String? = "",
    val RutaMini: String? = "",
    val RutaLocalMini: String? = "",
    val RutaLocal: String? = ""
)

data class AccesoriosModelos(
    var Concepto: String? = "",
    var Ruta: String? = "",
    var RutaLocal: String? = "",
    var SubTotal: String? = "",
    var Iva: String? = "",
    var Total: String? = ""
)

data class GamaColores(
    var Nombre: String? = "",
    var Ruta: String? = "",
    var RutaMini: String? = "",
    var RutaLocalMini: String? = "",
    var RutaLocal: String? = ""
)