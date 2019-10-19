package com.bargaintechnology.vantagecircle.core

import android.app.Application
import com.bargaintechnology.vantagecircle.core.application.AppApplication
import com.bargaintechnology.vantagecircle.core.util.HttpUtil

data class VantageCircle(
    val application: Application,
    val authToken: String?,
    val buildFlavour: BuildFlavour
) {
    init {
        AppApplication.application = application
        AppApplication.userAuthToken = authToken

        when (buildFlavour) {
            BuildFlavour.TEST -> HttpUtil.getInstance().isProduction = false
            BuildFlavour.PRODUCTION -> HttpUtil.getInstance().isProduction = true
        }
    }
}

enum class BuildFlavour {
    PRODUCTION, TEST
}
