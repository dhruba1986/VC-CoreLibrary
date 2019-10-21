package com.bargaintechnology.vantagecirclr.giftcard.apiService

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RestApiService {
//    @GET("movie/popular?language=en-US&region=US&page=1")
//    fun fetchMoviesByType(): Observable<MovieApiResponse>

    //Gift voucher
    @GET("/api/v1/giftvouchers")
    fun getGiftVouchers(@Query("showOutOfStock") showOutOfStock: Boolean): Call<Any>

    @GET("/api/v1/giftvouchers")
    fun getGiftVouchers(@Query("showOutOfStock") showOutOfStock: Boolean, @Query("filterBy") filterBy: String, @Query("filterByValues") filterByValue: String): Call<Any>
}