package kr.co.lion.yeominrak

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.core.content.ContextCompat
import kr.co.lion.yeominrak.model.Post
import kr.co.lion.yeominrak.model.UserModel
import kr.co.lion.yeominrak.model.Week
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class Util {
    companion object{

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


        fun stringToWeek(weekStr:String): Week = when(weekStr){
            Week.WEEK_SUNDAY.str -> Week.WEEK_SUNDAY
            Week.WEEK_MONDAY.str -> Week.WEEK_MONDAY
            Week.WEEK_TUESDAY.str -> Week.WEEK_TUESDAY
            Week.WEEK_WEDNESDAY.str -> Week.WEEK_WEDNESDAY
            Week.WEEK_THURSDAY.str -> Week.WEEK_THURSDAY
            Week.WEEK_FRIDAY.str -> Week.WEEK_FRIDAY
            Week.WEEK_SATURDAY.str -> Week.WEEK_SATURDAY
            else -> Week.WEEK_WEDNESDAY
        }

        fun weekToString(week: Week):String = when(week) {
            Week.WEEK_SUNDAY -> Week.WEEK_SUNDAY.str
            Week.WEEK_MONDAY -> Week.WEEK_MONDAY.str
            Week.WEEK_TUESDAY -> Week.WEEK_TUESDAY.str
            Week.WEEK_WEDNESDAY -> Week.WEEK_WEDNESDAY.str
            Week.WEEK_THURSDAY -> Week.WEEK_THURSDAY.str
            Week.WEEK_FRIDAY -> Week.WEEK_FRIDAY.str
            Week.WEEK_SATURDAY -> Week.WEEK_SATURDAY.str
            else -> Week.WEEK_WEDNESDAY.str
        }

        fun convertBitmapToByteArray(bitmap: Bitmap): ByteArray {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            return outputStream.toByteArray()
        }
//----------------------------------------------------------


        fun convertByteArrayToBitmap(byteArray: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        }


    }
}