package kr.co.lion.yeominrak

import java.text.SimpleDateFormat
import java.util.Date

class Util {
    companion object{
        val postList = mutableListOf<Post>(
            Post("테스트 글1","테스트 내용1","양승국",Util.getCurrentDate(),Week.WEEK_SUNDAY),
            Post("테스트 글3","테스트 내용3","안영준",Util.getCurrentDate(),Week.WEEK_SATURDAY),
            Post("테스트 글8","테스트 내용8","최재원",Util.getCurrentDate(),Week.WEEK_WEDNESDAY),
            Post("테스트 글1","테스트 내용1","양승국",Util.getCurrentDate(),Week.WEEK_SUNDAY),
            Post("테스트 글3","테스트 내용3","안영준",Util.getCurrentDate(),Week.WEEK_SATURDAY),
            Post("테스트 글8","테스트 내용8","최재원",Util.getCurrentDate(),Week.WEEK_WEDNESDAY),
            Post("테스트 글1","테스트 내용1","양승국",Util.getCurrentDate(),Week.WEEK_SUNDAY),
            Post("테스트 글3","테스트 내용3","안영준",Util.getCurrentDate(),Week.WEEK_SATURDAY),
            Post("테스트 글8","테스트 내용8","최재원",Util.getCurrentDate(),Week.WEEK_WEDNESDAY),
            Post("테스트 글1","테스트 내용1","양승국",Util.getCurrentDate(),Week.WEEK_SUNDAY),
            Post("테스트 글3","테스트 내용3","안영준",Util.getCurrentDate(),Week.WEEK_SATURDAY),
            Post("테스트 글8","테스트 내용8","최재원",Util.getCurrentDate(),Week.WEEK_WEDNESDAY),
            Post("테스트 글1","테스트 내용1","양승국",Util.getCurrentDate(),Week.WEEK_SUNDAY),
            Post("테스트 글3","테스트 내용3","안영준",Util.getCurrentDate(),Week.WEEK_SATURDAY),
            Post("테스트 글8","테스트 내용8","최재원",Util.getCurrentDate(),Week.WEEK_WEDNESDAY)
        )

        val testUser = User("안영준","ayz1070",Week.WEEK_SATURDAY)

        val myWeekList = mutableListOf<Post>(

        )

        fun getCurrentDate(): String {
            val currentTime = Date()
            val timeFormat = SimpleDateFormat("yyyy년 mm월 dd일 hh시 mm분 ss초")
            return timeFormat.format(currentTime)
        }

    }
}