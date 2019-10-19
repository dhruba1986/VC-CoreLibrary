package com.bargaintechnology.vantagecircle.core.network.error

import com.bargaintechnology.vantagecircle.core.network.exception.NoConnectivityException
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorHandler{

    fun getErrorMessage(response: ResponseBody?, responseCode: Int): ErrorResponse {
        val responseModel = ErrorResponse
        responseModel.errorMessage = getStringMessage(response?.string())
        responseModel.errorType = getErrorType(responseCode)
        responseModel.responseCode = responseCode
        return responseModel
    }

    fun getErrorMessage(throwable: Throwable): ErrorResponse {
        val responseModel = ErrorResponse
        when (throwable) {
            is NoConnectivityException -> {
                responseModel.errorMessage = ErrorMessage.msgErrConnectivity
                responseModel.errorType = ErrorType.CONNECTIVITY
            }
            is SocketTimeoutException -> {
                responseModel.errorMessage = ErrorMessage.msgErrTimeout
                responseModel.errorType = ErrorType.TIMEOUT
            }
            else -> {
                responseModel.errorMessage = ErrorMessage.msgErrDefault
                responseModel.errorType = ErrorType.SERVER
            }
        }
        responseModel.responseCode = ResponseCode.codeUndefiend
        return responseModel
    }

    private fun getErrorType(code: Int): ErrorType? {
        return when (code) {
            ResponseCode.codeAuthError -> ErrorType.AUTHORIZATION
            ResponseCode.codeVerification -> ErrorType.VERIFICATION
            else -> ErrorType.SERVER
        }
    }


    private fun getStringMessage(responseString: String?): String {
        responseString?.let { it ->
            return try {
                val result = JSONObject(it)
                when {
                    result.has("err") -> result.getString("err")
                    result.has("status_message") -> result.getString("status_message")
                    else -> ErrorMessage.msgErrDefault
                }
            } catch (e: IOException) {
                ErrorMessage.msgErrDefault
            } catch (e: JSONException) {
                ErrorMessage.msgErrDefault
            }
        }?: return ErrorMessage.msgErrDefault
    }
}