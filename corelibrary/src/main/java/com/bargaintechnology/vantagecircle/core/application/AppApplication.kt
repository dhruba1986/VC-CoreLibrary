package com.bargaintechnology.vantagecircle.core.application

import android.app.Application

class AppApplication{
    companion object{
        lateinit var application: Application
        var userAuthToken: String? = null
    }
}