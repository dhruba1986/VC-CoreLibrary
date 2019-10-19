package com.bargaintechnology.vantagecircle.core.network.interceptor

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit

internal class CachingControlInterceptor: Interceptor{
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val cacheControl = CacheControl.Builder()
            .maxStale(5, TimeUnit.MINUTES)
            .maxAge(5, TimeUnit.MINUTES)
            .build()
        requestBuilder.cacheControl(cacheControl)
        requestBuilder.header("Content-Type", "application/json")
        requestBuilder.header("Accept", "application/json")
        val request = requestBuilder.build()
        val originalResponse = chain.proceed(request)
        return originalResponse.newBuilder()
            .removeHeader("Pragma")
            .removeHeader("Cache-Control")
            .header("Cache-Control", "public, only-if-cached, max-stale=604800")
            .build()
    }
}