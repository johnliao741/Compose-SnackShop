package com.jazzhipster.snackshop.presentation.login.type

import com.jazzhipster.snackshop.R

sealed class SocialLogin(val iconRes:Int,val name:String){
    object Facebook:SocialLogin(R.mipmap.facebook_logo,"Facebook")
    object Google:SocialLogin(R.mipmap.google_logo,"Google")
    object Apple:SocialLogin(R.mipmap.apple_logo,"Apple")
}
