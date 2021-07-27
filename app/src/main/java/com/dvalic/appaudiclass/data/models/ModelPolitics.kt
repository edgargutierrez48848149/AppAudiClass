package com.dvalic.appaudiclass.data.models

import java.util.*

data class ModelPolitics(
        var IdMarca: String? = "",
        var TerminoYCondiciones: String? = "",
        var AvisoDePrivacidad: String? = "",
        var LinkVideo: String? = "",
        var LinkPaginaOficial: String? = "",
        var LinkPaginaAppStore: String? = "",
        var LinkPaginaAppStoreAndroid: String? = "",
        var PoliticasAgencias: ArrayList<PoliticasAgencias>? = arrayListOf(),
)

data class PoliticasAgencias(
        var IdAgencia: String? = "",
        var NombreAgencia: String? = "",
        var TerminoYCondiciones: String? = "",
        var AvisoDePrivacidad: String? = "",
        var Politicas: String? = "",
)