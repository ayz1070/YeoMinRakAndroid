package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.yeominrak.databinding.ActivityModifyBoardBinding

class ModifyBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityModifyBoardBinding
    var position:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setToolbar()
        initView()
        setEvent()
    }

    fun setToolbar(){
        binding.apply{
            toolbarModifyBoard.apply{
                title = "글 수정"
                setNavigationIcon(R.drawable.arrow_back_24px)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 완료 버튼 클릭 시
                        R.id.menu_item_complelte_modify_board -> {
                            val title = editTextTitleModifyBoard.text.toString()
                            val content = editTextContentModifyBoard.text.toString()
                            Util.postList[position] = Post(title,content,Util.testUser.userId,Util.getCurrentDate(),Util.testUser.userWeek)
                            finish()
                        }
                    }
                    true
                }
                inflateMenu(R.menu.menu_modify_board)


            }
        }
    }

    // -----------------------------------------------------------------
    fun initData(){
        position = intent?.getIntExtra("position",0)!!

    }

    fun initView(){
        binding.apply{
            editTextTitleModifyBoard.setText(Util.postList[position].title.toString())
            editTextContentModifyBoard.setText(Util.postList[position].content.toString())
        }
    }

    fun setEvent(){

    }
}
