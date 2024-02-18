package kr.co.lion.yeominrak

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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

        val testUserModel = UserModel(1,"안영준","ayz1070",Week.WEEK_SATURDAY)

        val myWeekList = mutableListOf<Post>(

        )

        fun getCurrentDate(): String {
            val currentTime = Date()
            val timeFormat = SimpleDateFormat("yyyy년 mm월 dd일 hh시 mm분 ss초")
            return timeFormat.format(currentTime)
        }

        fun getCurrentDayOfWeek(): String {
            val calendar = Calendar.getInstance()
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

            val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
            val currentDate = calendar.time

            return sdf.format(currentDate)
        }


        fun stringToWeek(weekStr:String):Week = when(weekStr){
            Week.WEEK_SUNDAY.str -> Week.WEEK_SUNDAY
            Week.WEEK_MONDAY.str -> Week.WEEK_MONDAY
            Week.WEEK_TUESDAY.str -> Week.WEEK_TUESDAY
            Week.WEEK_WEDNESDAY.str -> Week.WEEK_WEDNESDAY
            Week.WEEK_THURSDAY.str -> Week.WEEK_THURSDAY
            Week.WEEK_FRIDAY.str -> Week.WEEK_FRIDAY
            Week.WEEK_SATURDAY.str -> Week.WEEK_SATURDAY
            else ->Week.WEEK_WEDNESDAY
        }

        fun weekToString(week:Week):String = when(week) {
            Week.WEEK_SUNDAY -> Week.WEEK_SUNDAY.str
            Week.WEEK_MONDAY -> Week.WEEK_MONDAY.str
            Week.WEEK_TUESDAY -> Week.WEEK_TUESDAY.str
            Week.WEEK_WEDNESDAY -> Week.WEEK_WEDNESDAY.str
            Week.WEEK_THURSDAY -> Week.WEEK_THURSDAY.str
            Week.WEEK_FRIDAY -> Week.WEEK_FRIDAY.str
            Week.WEEK_SATURDAY -> Week.WEEK_SATURDAY.str
            else -> Week.WEEK_WEDNESDAY.str
        }

    }
}