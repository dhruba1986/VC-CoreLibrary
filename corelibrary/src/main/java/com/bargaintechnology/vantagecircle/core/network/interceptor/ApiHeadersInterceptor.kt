package com.bargaintechnology.vantagecircle.core.network.interceptor

import com.bargaintechnology.vantagecircle.core.network.CheckInternetConnection
import com.bargaintechnology.vantagecircle.core.network.exception.NoConnectivityException
import com.bargaintechnology.vantagecircle.core.util.HttpUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class ApiHeadersInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (CheckInternetConnection().check()) {
            throw NoConnectivityException()
        }
        val newRequest = chain.request().newBuilder()
            .headers(HttpUtil.getInstance().setHeaders())
            .method(chain.request().method(), chain.request().body())
            .build()
        return chain.proceed(newRequest)
    }
}

