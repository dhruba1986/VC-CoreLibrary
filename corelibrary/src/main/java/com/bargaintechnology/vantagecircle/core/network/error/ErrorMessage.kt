package com.bargaintechnology.vantagecircle.core.network.error

import com.bargaintechnology.vantagecircle.core.R
import com.bargaintechnology.vantagecircle.core.application.AppApplication

internal object ErrorMessage {
    val msgErrDefault = AppApplication.application.getString(R.string.message_error_default)
    val msgErrConnectivity = AppApplication.application.getString(R.string.message_error_no_internet)
    val msgErrTimeout = AppApplication.application.getString(R.string.message_error_time_out)

}