package com.bargaintechnology.vantagecircle.core.network.manager

import android.os.Handler
import android.os.Looper
import com.bargaintechnology.vantagecircle.core.config.Gson
import com.bargaintechnology.vantagecircle.core.network.error.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiCallManager {

    private val uiHandler = Handler(Looper.getMainLooper())

    fun apiCall(objectCall: Call<Any>?, TAG: String, apiListener: ApiContract.ApiListener) {

        objectCall?.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                uiHandler.post {
                    if (response.code() == 200 && response.body() != null) {
                        apiListener.apiCallSuccess(response.code(), Gson.gson?.toJson(response.body()), TAG)
                    } else {
                        apiListener.apiCallFailed(ErrorHandler.getErrorMessage(response.errorBody(), response.code()), TAG)
                    }
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                uiHandler.post {
                    apiListener.apiCallFailed(ErrorHandler.getErrorMessage(t), TAG)
                }
            }
        })
    }
}