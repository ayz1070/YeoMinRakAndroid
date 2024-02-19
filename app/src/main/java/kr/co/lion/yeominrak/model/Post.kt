package kr.co.lion.yeominrak.model

data class Post(
    var postTitle:String?,
    var postContent:String?,
    var postUserName:String?,
    var postUserId:String?,
    var postDate:String?,
    var postWeek: Week
)
