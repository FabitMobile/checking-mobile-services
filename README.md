# checking-mobile-services

Бибилиотека позволяет получить и узнать типы доступных мобильных сервисов на устройстве. 

Поддерживуются следующие варианты мобильных сервисов

    GOOGLE
    HUAWEI
    UNKNOWN

Пример 

    val mobileServices = MobileServicesProvider.getInstance()
    val mobileServiceType = mobileServices.getCurrentMobileServiceType(context)

    val locationProvider = when (mobileServicesType) {
        HUAWEI -> HMSFusedLocationProvider(context, locationProviderConfig)
        GOOGLE -> GoogleFusedLocationProvider(context, locationProviderConfig)
    }
