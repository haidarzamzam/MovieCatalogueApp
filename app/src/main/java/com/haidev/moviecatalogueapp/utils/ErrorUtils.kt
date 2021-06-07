package com.haidev.moviecatalogueapp.utils

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

object ErrorUtils {
    fun getErrorThrowableMsg(error: Throwable): String = when (error) {
        is HttpException ->
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> "Tidak dapat mengakses data"
                HttpsURLConnection.HTTP_NOT_FOUND -> "Data tidak ditemukan"
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Terjadi gangguan pada server"
                HttpsURLConnection.HTTP_BAD_REQUEST -> "Data tidak sesuai"
                HttpsURLConnection.HTTP_FORBIDDEN -> "Sesi telah berakhir"
                else -> "Oops, Terjadi gangguan, coba lagi beberapa saat"
            }
        is UnknownHostException -> "Unknown Error"
        is ConnectException -> "No internet connected"
        is SocketTimeoutException -> "No internet connected"
        is Errors.OfflineException -> "No internet connected"
        is Errors.FetchException -> "Fetch exception"
        else -> "Terjadi kesalahan"
    }
}