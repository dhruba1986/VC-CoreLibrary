package com.bargaintechnology.vantagecircle.core.network.manager

import com.bargaintechnology.vantagecircle.core.network.error.ErrorResponse

interface ApiContract {
    interface ApiListener {
        fun apiCallSuccess(responseCode: Int?, responseJsonString: String?, TAG: String?)

        fun apiCallFailed(errorResponse: ErrorResponse?, TAG: String?)
    }

//    interface DatabaseListener {
//        fun databaseSuccess(dataModel: DataModel, TAG: String)
//
//        fun databaseFailed(errorResponse: ErrorResponse, TAG: String)
//    }
//
//    interface RemoteConfigListener {
//        fun remoteConfigSuccess(config: FirebaseRemoteConfig)
//
//        fun remoteConfigFailed()
//    }
}