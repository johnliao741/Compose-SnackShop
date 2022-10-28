package com.jazzhipster.snackshop.presentation.login.type

sealed class LoginType {
    object Social:LoginType()
    object Email:LoginType()
}