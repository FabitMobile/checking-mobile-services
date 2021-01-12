package ru.fabit.checkingmobileservices.domain.provider

import android.content.Context
import ru.fabit.checkingmobileservices.domain.entity.MobileServices
import ru.fabit.checkingmobileservices.domain.entity.MobileServicesType

class UnknownServices : MobileServices {

    override val type: MobileServicesType
        get() = MobileServicesType.UNKNOWN

    override fun isAvailable(context: Context): Boolean {
        return false
    }

    override fun isInstalled(context: Context): Boolean {
        return true
    }
}