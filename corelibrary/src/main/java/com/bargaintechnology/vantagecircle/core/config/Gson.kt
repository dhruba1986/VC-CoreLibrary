package com.bargaintechnology.vantagecircle.core.config

import com.bargaintechnology.vantagecircle.core.R
import com.bargaintechnology.vantagecircle.core.application.AppApplication
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class Gson {
    companion object{
        val gson: Gson? = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat(AppApplication.application.getString(R.string.gson_date_format))
            .create()
    }
}