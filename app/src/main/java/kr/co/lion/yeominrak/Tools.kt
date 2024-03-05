package kr.co.lion.yeominrak

class Tools {
}

enum class FragmentNameMain(var str:String){
    MAIN_FRAGMENT("MainFragment"),
    BOARD_FRAGMENT("BoardFragment"),
    WEEK_BOARD_FRAGMENT("WeekBoardFragment"),
    CHECK_ATTENDANCE_FRAGMENT("CheckAttendanceFragment"),
    WRITE_BOARD_FRAGMENT("WriteBoardFragment"),
    SHOW_ONE_BOARD_FRAGMENT("ShowOneBoardFragment"),
    MODIFY_BOARD_FRAGMENT("ModifyBoardFragment"),
    SETTING_FRAGMENT("SettingFragment")
}

enum class FragmentNameLogin(var str:String){
    INPUT_USER_INFO_FRAGMENT("InputUserInfoFragment"),
}