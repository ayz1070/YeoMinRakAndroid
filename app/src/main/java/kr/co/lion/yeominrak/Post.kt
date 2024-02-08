package kr.co.lion.yeominrak

import android.os.Parcel
import android.os.Parcelable

data class Post(
    var title:String?,
    var content:String?,
    var nickname:String?,
    var date:String?,
    var week:Week
)
