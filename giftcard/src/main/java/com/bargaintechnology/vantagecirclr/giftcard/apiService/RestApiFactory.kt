package com.bargaintechnology.vantagecirclr.giftcard.apiService

import com.bargaintechnology.vantagecircle.core.network.config.HttpCallFactory

internal object RestApiFactory {
    fun apiService() : RestApiService?{
        return HttpCallFactory().provideRetrofit()?.create(RestApiService::class.java)
    }
}