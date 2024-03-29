package kr.co.lion.yeominrak.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import kr.co.lion.yeominrak.FragmentNameMain
import kr.co.lion.yeominrak.MainActivity
import kr.co.lion.yeominrak.PostDao
import kr.co.lion.yeominrak.R
import kr.co.lion.yeominrak.UserDao
import kr.co.lion.yeominrak.Util
import kr.co.lion.yeominrak.databinding.FragmentWriteBoardBinding
import kr.co.lion.yeominrak.model.Post


class WriteBoardFragment : Fragment() {
    lateinit var binding : FragmentWriteBoardBinding
    lateinit var postTitle:String
    lateinit var postContent:String
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWriteBoardBinding.inflate(inflater)
        initData()
        initView()
        setToolbar()
        setEvent()

        return binding.root
    }
    fun setToolbar(){
        binding.apply{
            toolbarWrite.apply{
                title = "글쓰기"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(R.drawable.arrow_back_24px)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentNameMain.WRITE_BOARD_FRAGMENT)
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
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!", Snackbar.LENGTH_SHORT).show()
                return
            }
            if(postContent.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!", Snackbar.LENGTH_SHORT).show()
                return
            }
            val user = UserDao.selectOneUser(requireContext(), 1)

            val post = Post(1, postTitle, postContent, user.userName, user.userNickname,
                Util.getCurrentDate(),user.userWeek)

            PostDao.insertPost(requireContext(), post)

            mainActivity.removeFragment(FragmentNameMain.WRITE_BOARD_FRAGMENT)
        }

    }


    fun initData(){
        mainActivity = activity as MainActivity
    }

    fun initView(){

    }

    fun setEvent(){

    }

}