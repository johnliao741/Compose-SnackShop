package com.jazzhipster.snackshop.remote.model

import com.jazzhipster.snackshop.remote.type.SnackCardItemType
import java.util.*

class GetListResponse(
    val category:List<SnackCardItem>
) :BaseResponse(){

}

class SnackCardItem(
    val type:SnackCardItemType,
    val title:String,
    val display:List<SnackItem>
){

}
class SnackItem(
    val id:Int,
    val name:String,
    val price:Double,
    val description:String,
    val rating:Double,
    val timestamp:Long,
    val urls:List<String>,
    val maskText:String?

){

}
fun Long.beforeTime():String{
    val now = Date().time
    val period = now - this
    return when{
        period in 60*1000..60*60*1000 -> "${(period/60/1000).toInt()}m"
        else-> "1m"
    }
}