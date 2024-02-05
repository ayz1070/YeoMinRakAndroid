package kr.co.lion.yeominrak

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.yeominrak.databinding.ActivityMainBinding
import kr.co.lion.yeominrak.databinding.ActivityWriteBoardBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class WriteBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityWriteBoardBinding
    lateinit var postData:Post
    lateinit var postTitle:String
    lateinit var postContent:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setToolbar()
        setEvent()

    }

    fun setToolbar(){
        binding.apply{
            toolbarWrite.apply{
                title = "글쓰기"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    setResult(RESULT_CANCELED)
                    finish()
                }
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_complete_write_board -> {
                            processInput()
                        }

                    }
                    true
                }
                inflateMenu(R.menu.menu_write_board)
            }

        }
    }

    fun processInput(){
        binding.apply{
            postTitle = editTextTitleWrite.text.toString()
            postContent = editTextContentWrite.text.toString()
            if(postTitle.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!",Snackbar.LENGTH_SHORT)
                return
            }
            if(postContent.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!",Snackbar.LENGTH_SHORT)
                return
            }
            postData = Post(postTitle,postContent,"테스트 유저",getCurrentDate())
            val resultIntent = Intent()
            resultIntent.putExtra("postData",postData)
            setResult(RESULT_OK,resultIntent)
            finish()
        }

    }

    fun getCurrentDate():String{
        val dateFormat = SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분", Locale.getDefault())
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }

    fun initData(){

    }

    fun initView(){

    }

    fun setEvent(){

    }

}