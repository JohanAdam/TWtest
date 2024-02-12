package com.nyan.twtest.domain.network

import retrofit2.HttpException
import java.io.IOException

/**
 * Handles error during api calls.
 */
class ErrorHandler(e: Exception) : Exception(e) {

    companion object {
        const val DEFAULT_ERROR_MESSAGE: String = "An Error has occurred. Please try again later."
        const val NO_NETWORK_ERROR_MESSAGE: String = "No internet connection detected. Please try again later."
        const val BAD_REQUEST_ERROR_MESSAGE: String = "Oh no. Bad Request."

        const val RESPONSE_CODE_NO_INTERNET = 1
        const val RESPONSE_CODE_FAILED = 2
        const val RESPONSE_CODE_BAD_REQUEST = 400
    }

    private val error: Throwable?

    init {
        e.printStackTrace()
        error = e
    }

    val responseCode: Int
        get() {
            return when (error) {
                is HttpException -> {
                    error.code()
                }
                is IOException -> {
                    RESPONSE_CODE_NO_INTERNET
                }
                else -> {
                    RESPONSE_CODE_FAILED
                }
            }
        }

    val errorMsg: String get() {
        return if (responseCode == RESPONSE_CODE_BAD_REQUEST) {
            BAD_REQUEST_ERROR_MESSAGE
        } else if (responseCode == RESPONSE_CODE_FAILED) {
            DEFAULT_ERROR_MESSAGE
        } else if (responseCode == RESPONSE_CODE_NO_INTERNET) {
            NO_NETWORK_ERROR_MESSAGE
        } else {
            error!!.message!!
        }
    }
}