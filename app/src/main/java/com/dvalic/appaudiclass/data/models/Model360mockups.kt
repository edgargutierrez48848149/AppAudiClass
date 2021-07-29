package com.dvalic.appaudiclass.data.models

import java.io.Serializable
import java.util.*

class Model360mockups(
    var IdMarca: String? = "",
    var Nombre: String? = "",
    var Maquetas: ArrayList<Maquetas>? = arrayListOf()
) : Serializable

data class Maquetas(
    var Version: String? = "",
    var Nombre: String? = "",
    var Anio: String? = "",
    var NombreApp: String? = "",
    var Ruta: String? = ""
) : Serializable