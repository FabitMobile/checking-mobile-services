package ru.fabit.checkingmobileservices.domain.entity

import android.content.Context

interface MobileServices {

    val type: MobileServicesType

    fun isAvailable(context: Context): Boolean

    fun isInstalled(context: Context): Boolean
}