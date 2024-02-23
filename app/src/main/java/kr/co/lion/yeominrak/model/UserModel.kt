package kr.co.lion.yeominrak.model

import android.net.Uri

data class UserModel(
    var idx:Int,
    var userName:String,
    var userNickname:String,
    var userWeek: Week,
    var userProfileImage:ByteArray
)
