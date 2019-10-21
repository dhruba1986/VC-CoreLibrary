package com.bargaintechnology.vantagecirclr.giftcard.initializer

import android.util.Log
import com.bargaintechnology.vantagecircle.core.network.error.ErrorResponse
import com.bargaintechnology.vantagecircle.core.network.manager.ApiCallManager
import com.bargaintechnology.vantagecircle.core.network.manager.ApiContract.ApiListener
import com.bargaintechnology.vantagecirclr.giftcard.apiService.RestApiFactory

object GiftCard{

    internal var apiListener: GiftCardListener? = null
    var showOutOfStock: Boolean = true

    fun getAllGiftCard(TAG: String){
        ApiCallManager().apiCall(RestApiFactory.apiService()?.getGiftVouchers(showOutOfStock), TAG, object : ApiListener{
            override fun apiCallSuccess(responseCode: Int?, responseJsonString: String?, TAG: String?) {
                apiListener?.onSuccess(responseJsonString, TAG)
            }

            override fun apiCallFailed(errorResponse: ErrorResponse?, TAG: String?) {
                Log.v("XYZ", "Api  call :: " + errorResponse?.errorMessage)
                apiListener?.onFailed(errorResponse, TAG)
            }
        })
    }

    fun getFilteredGiftCard(TAG: String, filterBy: String) {

        ApiCallManager().apiCall(RestApiFactory.apiService()?.getGiftVouchers(showOutOfStock, "tag", filterBy), TAG, object : ApiListener{
            override fun apiCallSuccess(responseCode: Int?, responseJsonString: String?, TAG: String?) {
                Log.v("XYZ", "Api call :: $responseJsonString")
            }

            override fun apiCallFailed(errorResponse: ErrorResponse?, TAG: String?) {
                Log.v("XYZ", "Api  call :: " + errorResponse?.errorMessage)
            }
        })
    }

}