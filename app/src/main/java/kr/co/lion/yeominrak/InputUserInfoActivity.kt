package kr.co.lion.yeominrak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kr.co.lion.yeominrak.databinding.ActivityInputUserInfoBinding

class InputUserInfoActivity : AppCompatActivity() {
    lateinit var binding:ActivityInputUserInfoBinding

    lateinit var userName:String
    lateinit var userId:String
    lateinit var userWeek:Week
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setEvent()

    }


    fun processInput(){
        binding.apply{
            userName = editTextName.text.toString()
            userId = editTextId.text.toString()
            val userWeekStr = textViewMenuInputUser.text.toString()

            if(userName.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!",Snackbar.LENGTH_SHORT).show()
                return
            }

            if(userId.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!",Snackbar.LENGTH_SHORT).show()
                return
            }

            if(userWeekStr.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!",Snackbar.LENGTH_SHORT).show()
                return
            }

            userWeek = Util.stringToWeek(userWeekStr)

            val userModel = UserModel(0,userName, userId, userWeek)
            UserDao.insertUser(this@InputUserInfoActivity,userModel)
        }

    }

    fun initData(){

    }

    fun initView(){
        binding.apply{
            val weekArray = arrayOf(
                Week.WEEK_SUNDAY.str,
                Week.WEEK_MONDAY.str,
                Week.WEEK_TUESDAY.str,
                Week.WEEK_WEDNESDAY.str,
                Week.WEEK_THURSDAY.str,
                Week.WEEK_FRIDAY.str,
                Week.WEEK_SATURDAY.str
            )
            (textInputLayoutMenuInputUser.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(weekArray)
        }
    }

    fun setEvent(){
        binding.apply{
            buttonCompleteInputUser.setOnClickListener {
                processInput()
                val mainIntent = Intent(this@InputUserInfoActivity,MainActivity::class.java)
                startActivity(mainIntent)
            }
        }

    }
}