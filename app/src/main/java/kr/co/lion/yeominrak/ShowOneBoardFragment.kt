package kr.co.lion.yeominrak

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kr.co.lion.yeominrak.databinding.FragmentShowOneBoardBinding


class ShowOneBoardFragment : Fragment() {
    lateinit var binding:FragmentShowOneBoardBinding
    lateinit var modifyBoardLauncher: ActivityResultLauncher<Intent>
    lateinit var mainActivity:MainActivity

    var position:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShowOneBoardBinding.inflate(inflater)
        initData()
        initView()
        setToolbar()
        setEvent()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initView()
    }

    fun setToolbar(){
        binding.apply{
            toolbarShowOne.apply{
                title = "자유게시판"

                setNavigationIcon(R.drawable.arrow_back_24px)

                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.SHOW_ONE_BOARD_FRAGMENT)
                }
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_modify_show_one -> {

                            // positon 값을 전달해야 한다. 추후 생각하자
                            mainActivity.replaceFragment(FragmentName.MODIFY_BOARD_FRAGMENT,true,true,null)
                        }
                        R.id.menu_item_delete_show_one -> {
                            Util.postList.removeAt(position)
                            mainActivity.removeFragment(FragmentName.MODIFY_BOARD_FRAGMENT)
                        }
                    }
                    true
                }
                inflateMenu(R.menu.menu_show_one_board)
            }
        }
    }



    fun initData(){
        position = arguments?.getInt("position")!!
        mainActivity = activity as MainActivity
    }

    fun initView(){
        binding.apply{
            editTextTitleShowOne.setText(Util.postList[position].postTitle)
            editTextContentShowOne.setText(Util.postList[position].postContent)
        }
    }


    fun setEvent(){
        // 테스트
    }


}