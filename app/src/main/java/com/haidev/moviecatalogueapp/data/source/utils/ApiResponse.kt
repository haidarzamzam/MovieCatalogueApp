package com.haidev.moviecatalogueapp.data.source.utils

import com.haidev.moviecatalogueapp.utils.enum.Status

class ApiResponse<T>(val status: Status, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> =
            ApiResponse(Status.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T? = null): ApiResponse<T> =
            ApiResponse(Status.EMPTY, body, msg)

        fun <T> error(msg: String, body: T? = null): ApiResponse<T> =
            ApiResponse(Status.ERROR, body, msg)
    }
}