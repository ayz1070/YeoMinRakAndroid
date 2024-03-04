package kr.co.lion.yeominrak.model

data class Post(
    var idx :Int,
    var postTitle:String?,
    var postContent:String?,
    var postUserName:String?,
    var postUserNickname:String?,
    var postDate:String?,
    var postWeek: Week
)
