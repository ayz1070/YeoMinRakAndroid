package kr.co.lion.yeominrak

import kr.co.lion.yeominrak.model.UserModel

data class Attendance(
    var userModel: UserModel,
    var isAttend:AttendState
)

enum class AttendState(val str:String){
    NOT_SELECTED("미정"),
    ATTEND("참석"),
    NOT_ATTEND("불참")
}

