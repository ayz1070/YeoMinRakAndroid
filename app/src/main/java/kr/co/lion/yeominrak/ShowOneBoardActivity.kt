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
        setToolbar()
        setEvent()
    }

    fun setToolbar(){
        binding.apply{
            toolbarShowOne.apply{
                title = "자유게시판"
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_modify_show_one -> {

                        }
                        R.id.menu_item_delete_show_one -> {

                        }
                    }
                    true
                }
                inflateMenu(R.menu.menu_show_one_board)
            }
        }
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
        // 테스트
    }
}