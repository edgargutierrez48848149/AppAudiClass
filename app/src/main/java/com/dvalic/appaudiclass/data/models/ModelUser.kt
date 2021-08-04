package com.dvalic.appaudiclass.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_table")
data class ModelUser(
    val IdCuenta: String? = "",
    @PrimaryKey
    val IdPersona: String = "-1",
    val Nombre: String? = "",
    val ApellidoPaterno: String? = "",
    val ApellidoMaterno: String? = "",
    val Correo: String? = "",
    val Clave: String? = "",
    val Token: String? = "",
    val LadaMovil: String? = "",
    val TelefonoMovil: String? = ""
):Parcelable