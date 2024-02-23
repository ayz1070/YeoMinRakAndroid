package kr.co.lion.yeominrak

import android.content.Context
import kr.co.lion.yeominrak.model.UserModel

class UserDao {
    companion object{
        // select one
        fun selectOneUser(context: Context, idx:Int): UserModel {
            // 쿼리문
            val sql = """select idx, userName, userNickname, userWeek, userProfileImage
                | from UserTable
                | where idx = ?
            """.trimMargin()

            // ?에 바인딩 될 값
            val args = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터를 가져온다.
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("userName")
            val idx3 = cursor.getColumnIndex("userNickname")
            val idx4 = cursor.getColumnIndex("userWeek")
            val idx5 = cursor.getColumnIndex("userProfileImage")

            val idx = cursor.getInt(idx1)
            val userName = cursor.getString(idx2)
            val userId = cursor.getString(idx3)
            val userWeekStr = cursor.getString(idx4)
            val userWeek = Util.stringToWeek(userWeekStr)
            val userProfileImage = cursor.getBlob(idx5)

            val userModel = UserModel(idx,userName,userId,userWeek,userProfileImage)

            dbHelper.close()
            return userModel
        }

        // select all
        fun selectAllStudent(context:Context):MutableList<UserModel>{
            val sql = """select idx, userName, userNickname, userWeek, userProfileImage
                | from UserTable
                | order by idx desc
            """.trimMargin()

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql,null)

            // 데이터를 담을 리스트
            val userList = mutableListOf<UserModel>()

            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("userName")
                val idx3 = cursor.getColumnIndex("userNickname")
                val idx4 = cursor.getColumnIndex("userWeek")
                val idx5 = cursor.getColumnIndex("userProfileImage")

                val idx = cursor.getInt(idx1)
                val userName = cursor.getString(idx2)
                val userNickname = cursor.getString(idx3)
                val userWeekStr = cursor.getString(idx4)
                val userWeek = Util.stringToWeek(userWeekStr)
                val userProfileImage = cursor.getBlob(idx5)

                val userModel = UserModel(idx,userName,userNickname,userWeek,userProfileImage)


                userList.add(userModel)
            }
            dbHelper.close()
            return userList
        }

        // insert
        fun insertUser(context:Context, userModel: UserModel){
            // 쿼리문
            val sql = """
                insert into UserTable
                (userName,userNickname,userWeek,userProfileImage)
                values(?, ?, ?, ?)
            """.trimIndent()

            // ? 에 바인딩 될 값
            val args = arrayOf(userModel.userName,userModel.userNickname,userModel.userWeek,userModel.userProfileImage)
            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // update
        fun updateUser(context:Context, userModel: UserModel){
            // 쿼리문
            val sql = """update UserTable
            | set userName = ?, userNickname = ?, userWeek = ?, userImage = ?
            | where idx = ?
        """.trimMargin()

            // ?에 바인딩 될 값
            val args = arrayOf(userModel.userName,userModel.userNickname,userModel.userWeek, userModel.userProfileImage)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // delete
        fun deleteStudent(context:Context, idx:Int){
            // 쿼리문
            val sql = """delete from UserTable
            | where idx = ?
        """.trimMargin()

            // ? 에 바인딩될 값
            val args = arrayOf(idx)

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}