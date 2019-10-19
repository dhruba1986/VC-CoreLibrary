package com.bargaintechnology.vantagecircle.core.config

import com.bargaintechnology.vantagecircle.core.util.HttpUtil

internal class CloudinaryConfig {
    companion object{
        val config = provideConfig()

        private fun provideConfig(): HashMap<String, String> {
            val config = java.util.HashMap<String, String>()
            if (HttpUtil.getInstance().isProduction) {
                config["cloud_name"] = "vantagecircle"
                config["api_key"] = "139821972751735"
                config["api_secret"] = "axep09TTw4g-zztGUymyGKeYikM"
                config["secure"] = "true"
            } else {
                config["cloud_name"] = "du0mlu2n6"
                config["api_key"] = "268864137955583"
                config["api_secret"] = "KgsXDGUCsDoNHtU-ikL6xq-w5cg"
                config["secure"] = "true"
            }
            return config
        }
    }
}