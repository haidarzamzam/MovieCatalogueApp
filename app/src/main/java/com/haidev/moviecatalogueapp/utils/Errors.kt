package com.haidev.moviecatalogueapp.utils

sealed class Errors
    (msg: String) : Exception(msg) {
    class OfflineException(msg: String = "Not Connected to Internet") : Errors(msg)
    class FetchException(msg: String) : Errors(msg)
}