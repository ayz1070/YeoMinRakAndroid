package kr.co.lion.yeominrak

import android.content.Context
import kr.co.lion.yeominrak.model.Post

class PostDao {

    companion object{

        // selectOne
        fun selectOnePost(context: Context, idx:Int):Post{
            val sql = """select idx, postTitle, postContent, postUserName, postUserNickname, postDate, postWeek
                | from PostTable
                | where idx = ?
            """.trimMargin()

            // ? 에 바인딩 될 값
            val args = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, args)

            // 데이터를 가져온다
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("postTitle")
            val idx3 = cursor.getColumnIndex("postContent")
            val idx4 = cursor.getColumnIndex("postUserName")
            val idx5 = cursor.getColumnIndex("postUserNickname")
            val idx6 = cursor.getColumnIndex("postDate")
            val idx7 = cursor.getColumnIndex("postWeek")

            val idx = cursor.getInt(idx1)
            val postTitle = cursor.getString(idx2)
            val postContent = cursor.getString(idx3)
            val postUserName = cursor.getString(idx4)
            val postUserNickname = cursor.getString(idx5)
            val postDate = cursor.getString(idx6)
            val postWeek = cursor.getString(idx7)

            val post = Post(idx,postTitle, postContent, postUserName, postUserNickname, postDate ,Util.stringToWeek(postWeek))

            dbHelper.close()
            return post
        }




        // Select All
        fun selectAllPost(context:Context):MutableList<Post>{
            val sql = """select idx, postTitle, postContent, postUserName, postUserNickname, postDate, postWeek
                | from PostTable
                | order by idx desc
            """.trimMargin()

            // 쿼리 실행
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

            // 데이터를 담을 리스트
            val postList = mutableListOf<Post>()

            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("postTitle")
                val idx3 = cursor.getColumnIndex("postContent")
                val idx4 = cursor.getColumnIndex("postUserName")
                val idx5 = cursor.getColumnIndex("postUserNickname")
                val idx6 = cursor.getColumnIndex("postDate")
                val idx7 = cursor.getColumnIndex("postWeek")

                val idx = cursor.getInt(idx1)
                val postTitle = cursor.getString(idx2)
                val postContent = cursor.getString(idx3)
                val postUserName = cursor.getString(idx4)
                val postUserNickname = cursor.getString(idx5)
                val postDate = cursor.getString(idx6)
                val postWeek = cursor.getString(idx7)

                val post = Post(idx,postTitle, postContent, postUserName, postUserNickname, postDate ,Util.stringToWeek(postWeek))

                postList.add(post)
            }
            dbHelper.close()
            return postList
        }


        // insert
        fun insertPost(context:Context, post:Post){
            val sql = """insert into PostTable
                (postTitle, postContent, postUserName, postUserNickname, postDate, postWeek)
                values(?, ?, ?, ?, ? ,?)
            """.trimIndent()

            val args = arrayOf(post.postTitle, post.postContent, post.postUserName, post.postUserNickname, post.postDate, Util.weekToString(post.postWeek))

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }

    fun updatePost(context:Context, post: Post){
        val sql = """update PostTable
            |set postTitle = ?, postContent = ?, postUserName = ?, postUserNickname= ?, postDate = ?, postWeek = ? 
            | where idx = ?
        """.trimMargin()

        // ?에 바인딩 될 값
        val args = arrayOf(post.postTitle, post.postContent, post.postUserName, post.postUserNickname, post.postDate, Util.weekToString(post.postWeek))

        val dbHelper = DBHelper(context)
        dbHelper.writableDatabase.execSQL(sql, args)
        dbHelper.close()
    }

    fun deletePost(context: Context, idx:Int){
        // 쿼리문
        val sql = """delete from PostTable
            |where idx = ?
        """.trimMargin()

        val args = arrayOf(idx)
        val dbHelper = DBHelper(context)
        dbHelper.writableDatabase.execSQL(sql, args)
        dbHelper.close()
    }
}