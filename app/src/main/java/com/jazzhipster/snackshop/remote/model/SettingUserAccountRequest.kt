package com.jazzhipster.snackshop.remote.model

import java.io.Serializable

class SettingUserAccountRequest(
    val img:String? = null,
    val name:String,
    val height:Double,
    val weight:Double,
    val location:String,
    val goal:SnackGoalType,
    val selectSnackId:List<String>
) :Serializable{
}
enum class SnackGoalType(val type:Int){
    Nothing(-1),
    NoGoal(0),
    PartyTime(1),
    HealthWay(2)
}