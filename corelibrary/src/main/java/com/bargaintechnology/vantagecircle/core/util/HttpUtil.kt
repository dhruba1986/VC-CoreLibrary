package com.bargaintechnology.vantagecircle.core.util

import com.bargaintechnology.vantagecircle.core.BuildConfig
import com.bargaintechnology.vantagecircle.core.application.AppApplication
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit

internal open class HttpUtil {
    var isProduction: Boolean = true
    private val productionPrefix = "https://api.vantagecircle.com"
    private val testPrefix = "https://api.vantagecircle.in"

    companion object {
        private val mInstance : HttpUtil = HttpUtil()

        @Synchronized
        fun getInstance() : HttpUtil{
            return mInstance
        }
    }


    fun getCorrectUrlPrefix(url: String): String {
        return if (isProduction) { productionPrefix + url } else { testPrefix + url }
    }

    fun getBaseUrl(): String {
        return if (isProduction) { productionPrefix } else { testPrefix }
    }

    fun getHostName(): String {
        return if (isProduction) { "api.vantagecircle.com" } else { "api.vantagecircle.in" }
    }

    fun setHeaders(): Headers {
        return Headers.of(setHeaderAuth())
    }

    private fun setHeaderAuth(): HashMap<String, String> {
        val params = HashMap<String, String>()

        AppApplication.userAuthToken?.let { it ->
            params["X-XSRF-TOKEN"] = it
        }

        params["device"] = "android"
        params["appVersion"] = BuildConfig.VERSION_NAME
        params["appName"] = "VantageCircle"
        return params
    }

    @Throws(IOException::class)
    fun getHttpUrlConnection(api: String): HttpURLConnection {
        val urlUpload = getCorrectUrlPrefix(api)
        val url = URL(urlUpload)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.doOutput = true
        httpURLConnection.useCaches = false
        AppApplication.userAuthToken?.let { it ->
            httpURLConnection.setRequestProperty("X-XSRF-TOKEN", it)
        }
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive")
        httpURLConnection.setRequestProperty("device", "android")
        httpURLConnection.setRequestProperty("appVersion", BuildConfig.VERSION_NAME)
        httpURLConnection.setRequestProperty("appName", "VantageCircle")
        return httpURLConnection
    }

    //TODO :: Reqd on enabling firebase analytics
/*    fun getEncodedParams(params: HashMap<String, String>): String {
        val sb = StringBuilder()
        for ((key, param_value) in params) {
            var value: String? = null
            try {
                value = URLEncoder.encode(param_value, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                TrackerManager.getInstance().trackException(e)
            }

            if (sb.length > 0) {
                sb.append("&")
            }
            sb.append(key).append("=").append(value)
        }
        return sb.toString()
    } */

    fun getBaseBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient.Builder().cache(cache)
            .retryOnConnectionFailure(true)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
    }

    fun getLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    fun getHttpCache(): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache( AppApplication.application.cacheDir, cacheSize.toLong())
    }

    fun getCertificate(): String {
        return if (isProduction) {
            "sha256/klO23nT2ehFDXCfx3eHTDRESMz3asj1muO+4aIdjiuY="
        } else {
            "sha256/ds11K4DSWhnu5zZv8pByYE+TUyUflOoIvmyDaWOf7qQ="
        }
    }
}