package com.example.memo.utils

interface RemoteErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}

enum class ErrorType {
    NETWORK,
    TIMEOUT,
    SESSION_EXPIRED,
    UNKNOWN
}