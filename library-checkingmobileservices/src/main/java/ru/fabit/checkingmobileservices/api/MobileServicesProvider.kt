package ru.fabit.checkingmobileservices.api

import android.content.Context
import ru.fabit.checkingmobileservices.domain.entity.MobileServices
import ru.fabit.checkingmobileservices.domain.entity.MobileServicesType
import ru.fabit.checkingmobileservices.domain.entity.MobileServicesType.UNKNOWN
import ru.fabit.checkingmobileservices.domain.provider.UnknownServices
import ru.fabit.checkingmobileservices.domain.provider.google.GoogleServices
import ru.fabit.checkingmobileservices.domain.provider.huawei.HuaweiServices

class MobileServicesProvider private constructor() {

    private val googleServices: MobileServices by lazy {
        GoogleServices()
    }

    private val huaweiServices: MobileServices by lazy {
        HuaweiServices()
    }

    private val unknownServices: MobileServices by lazy {
        UnknownServices()
    }

    companion object {
        private val mobileServiceProvider = MobileServicesProvider()

        fun getInstance(): MobileServicesProvider {
            return mobileServiceProvider
        }
    }

    fun getMobileServiceTypes(context: Context): List<MobileServicesType> {
        val listMobileServices = listOf(
            googleServices,
            huaweiServices
        )
        return listMobileServices.filter { it.isInstalled(context) }.map { it.type }
    }

    fun getCurrentMobileServiceType(context: Context): MobileServicesType {
        val listMobileServices = listOf(
            googleServices,
            huaweiServices,
            unknownServices
        )
        val availableMobileServices = listMobileServices
            .filter { it.isAvailable(context) }
        return when (availableMobileServices.count()) {
            1 -> availableMobileServices.first().type
            0 -> listMobileServices.filter { it.isInstalled(context) }.findSuitableType()
            else -> availableMobileServices.findSuitableType()
        }
    }

    private fun List<MobileServices>.findSuitableType(): MobileServicesType {
        return (firstOrNull { it is GoogleServices }
            ?: firstOrNull { it is HuaweiServices }
            ?: firstOrNull { it is UnknownServices })?.type
            ?: UNKNOWN
    }
}