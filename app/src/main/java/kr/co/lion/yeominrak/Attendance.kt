package kr.co.lion.yeominrak

data class Attendance(
    var user:User,
    var isAttend:AttendState
)

enum class AttendState(val str:String){
    NOT_SELECTED("미정"),
    ATTEND("참석"),
    NOT_ATTEND("불참")
}

