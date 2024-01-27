package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kr.co.lion.yeominrak.databinding.ActivityInputInitialUserInfoBinding

class InputInitialUserInfoActivity : AppCompatActivity() {

    val weeks = arrayOf("월요일","화요일","수요일","목요일","금요일","토요일","일요일")

    lateinit var binding:ActivityInputInitialUserInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputInitialUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (binding.textFieldWeek.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(weeks)


    }

    fun initView(){

    }

    fun setEvent(){

    }
}