package ru.fabit.checkingmobileservices.domain.provider.huawei

import android.content.Context
import com.huawei.hms.api.ConnectionResult.*
import com.huawei.hms.api.HuaweiApiAvailability
import ru.fabit.checkingmobileservices.domain.entity.MobileServices
import ru.fabit.checkingmobileservices.domain.entity.MobileServicesType

class HuaweiServices : MobileServices {

    override val type: MobileServicesType
        get() = MobileServicesType.HUAWEI

    override fun isAvailable(context: Context): Boolean {
        return when (HuaweiApiAvailability.getInstance().isHuaweiMobileNoticeAvailable(context)) {
            SUCCESS -> true
            else -> false
        }
    }

    override fun isInstalled(context: Context): Boolean {
        return when (HuaweiApiAvailability.getInstance().isHuaweiMobileNoticeAvailable(context)) {
            SUCCESS,
            SERVICE_UPDATING,
            SERVICE_DISABLED,
            SERVICE_VERSION_UPDATE_REQUIRED,
            SERVICE_MISSING_PERMISSION -> true
            else -> false
        }
    }

    public fun getEmuiApiLvl(): Int {
        val clazz = Class.forName("android.os.SystemProperties")
        val get = clazz.getMethod("getInt", String::class.java, Int::class.java)
        val currentApiLevel = get.invoke(clazz, EMUI_API, UNKNOWN_API_LEVEL) as Int
        return currentApiLevel
    }

    private companion object {
        const val EMUI_API = "ro.build.hw_emui_api_level"
        const val UNKNOWN_API_LEVEL = -1
    }
}