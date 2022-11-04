package com.jazzhipster.snackshop.remote.model

class GetSnackDetailResponse(
    val img:String,
    val price: Double,
    val rating:Double,
    val time:Long,
    val description:String,
    val recommended:SnackCardItem
):BaseResponse() {
}