package kr.co.lion.yeominrak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.yeominrak.databinding.ActivityShowOneBoardBinding

class ShowOneBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityShowOneBoardBinding
    lateinit var modifyBoardLauncher:ActivityResultLauncher<Intent>

    var position:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowOneBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setLauncher()
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
                            val modifyBoardIntent = Intent(this@ShowOneBoardActivity,ModifyBoardActivity::class.java)
                            modifyBoardLauncher.launch(modifyBoardIntent)
                        }
                        R.id.menu_item_delete_show_one -> {
                            Util.postList.removeAt(position)
                            finish()
                        }
                    }
                    true
                }
                inflateMenu(R.menu.menu_show_one_board)
            }
        }
    }

    fun setLauncher(){
        val contract1 = ActivityResultContracts.StartActivityForResult()
        modifyBoardLauncher = registerForActivityResult(contract1){

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