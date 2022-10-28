package com.jazzhipster.snackshop.remote.result

sealed class Result<out R>{
    object Initial:Result<Nothing>()
    object Loading:Result<Nothing>()
    data class Success<T>(val data:T):Result<T>()
    data class Error(val e:Throwable):Result<Nothing>()
}
