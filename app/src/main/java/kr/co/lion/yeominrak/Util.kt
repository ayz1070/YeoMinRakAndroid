package kr.co.lion.yeominrak

import java.text.SimpleDateFormat
import java.util.Date

class Util {
    companion object{
        val postList = mutableListOf<Post>()
        val testUser = User("안영준","ayz1070",Week.WEEK_WEDNESDAY)

        fun getCurrentDate(): String {
            val currentTime = Date()
            val timeFormat = SimpleDateFormat("yyyy년 mm월 dd일 hh시 mm분 ss초")
            return timeFormat.format(currentTime)
        }

    }

}