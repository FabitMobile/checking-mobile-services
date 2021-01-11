package ru.fabit.checkingmobileservices.domain.provider.google

import android.content.Context
import com.google.android.gms.common.ConnectionResult.*
import com.google.android.gms.common.GoogleApiAvailability
import ru.fabit.checkingmobileservices.domain.entity.MobileServices
import ru.fabit.checkingmobileservices.domain.entity.MobileServicesType

class GoogleServices : MobileServices {

    override val type: MobileServicesType
        get() = MobileServicesType.GOOGLE

    override fun isAvailable(context: Context): Boolean {
        return when (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)) {
            SUCCESS -> true
            else -> false
        }
    }

    override fun isInstalled(context: Context): Boolean {
        return when (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context)) {
            SUCCESS,
            SERVICE_UPDATING,
            SERVICE_INVALID,
            SERVICE_DISABLED,
            SERVICE_VERSION_UPDATE_REQUIRED,
            SERVICE_MISSING_PERMISSION -> true
            else -> false
        }
    }
}