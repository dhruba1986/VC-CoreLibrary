package com.bargaintechnology.vantagecircle.core.network.config

import com.bargaintechnology.vantagecircle.core.config.Gson
import com.bargaintechnology.vantagecircle.core.network.interceptor.ApiHeadersInterceptor
import com.bargaintechnology.vantagecircle.core.network.interceptor.CachingControlInterceptor
import com.bargaintechnology.vantagecircle.core.util.HttpUtil
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpCallFactory {
    

    fun provideRetrofit(): Retrofit? {
        return Gson.gson?.let { it ->
            Retrofit.Builder()
                .client(provideHttpClient())
                .addConverterFactory(GsonConverterFactory.create(it))
                .baseUrl(HttpUtil.getInstance().getBaseUrl())
                .build()
        }
    }

    private fun provideHttpClient(): OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add(HttpUtil.getInstance().getHostName(), HttpUtil.getInstance().getCertificate())
            .build()

        return HttpUtil.getInstance().getBaseBuilder(HttpUtil.getInstance().getHttpCache())
            .addNetworkInterceptor(CachingControlInterceptor())
            .addInterceptor(ApiHeadersInterceptor())
            .addInterceptor(HttpUtil.getInstance().getLogginInterceptor())
            .certificatePinner(certificatePinner)
            .build()
    }


}