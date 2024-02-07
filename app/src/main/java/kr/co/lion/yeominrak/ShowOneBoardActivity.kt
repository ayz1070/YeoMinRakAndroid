package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.yeominrak.databinding.ActivityShowOneBoardBinding

class ShowOneBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityShowOneBoardBinding

    var position:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOneBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setEvent()
    }

    fun initData(){
        position = intent?.getIntExtra("position",0)!!
    }

    fun initView(){
        binding.apply{
            editTextTitleShowOne.setText(Util.postList[position].title)
            editTextContentShowOne.setText(Util.postList[position].content)
        }
    }

    fun setEvent(){

    }
}