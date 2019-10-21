package com.bargaintechnology.vantagecirclr.giftcard.initializer

import com.bargaintechnology.vantagecircle.core.network.error.ErrorResponse

interface GiftCardListener {
    fun onSuccess(responseJsonString: String?, tag: String?)
    fun onFailed(errorResponse: ErrorResponse?, tag: String?)
}
