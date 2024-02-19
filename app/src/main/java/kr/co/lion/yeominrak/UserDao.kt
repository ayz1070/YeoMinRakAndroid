package kr.co.lion.yeominrak

import android.content.Context
import kr.co.lion.yeominrak.model.UserModel

class UserDao {
    companion object{
        // select one
        fun selectOneUser(context: Context, idx:Int): UserModel {
            // 쿼리문
            val sql = """select idx, userName, userId, userWeekStr
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
            val idx3 = cursor.getColumnIndex("userId")
            val idx4 = cursor.getColumnIndex("userWeekStr")

            val idx = cursor.getInt(idx1)
            val userName = cursor.getString(idx2)
            val userId = cursor.getString(idx3)
            val userWeekStr = cursor.getString(idx4)
            val userWeek = Util.stringToWeek(userWeekStr)

            val userModel = UserModel(idx,userName,userId,userWeek)

            dbHelper.close()
            return userModel
        }

        // select all
        fun selectAllStudent(context:Context):MutableList<UserModel>{
            val sql = """select idx, userName, userId, userWeekStr
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
                val idx2 = cursor.getColumnIndex("userNameStr")
                val idx3 = cursor.getColumnIndex("userId")
                val idx4 = cursor.getColumnIndex("userWeek")

                val idx = cursor.getInt(idx1)
                val userName = cursor.getString(idx2)
                val userId = cursor.getString(idx3)
                val userWeekStr = cursor.getString(idx4)
                val userWeek = Util.stringToWeek(userWeekStr)

                val userModel = UserModel(idx,userName,userId,userWeek)


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
                (userName,userId,userWeek)
                values(?, ?, ?)
            """.trimIndent()

            // ? 에 바인딩 될 값
            val args = arrayOf(userModel.userName,userModel.userId,userModel.userWeek)
            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        // update
        fun updateUser(context:Context, userModel: UserModel){
            // 쿼리문
            val sql = """update UserTable
            | set userName = ?, userId = ?, userWeek = ?
            | where idx = ?
        """.trimMargin()

            // ?에 바인딩 될 값
            val args = arrayOf(userModel.userName,userModel.userId,userModel.userWeek)

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