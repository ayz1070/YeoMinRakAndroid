package kr.co.lion.yeominrak.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.yeominrak.FragmentNameMain
import kr.co.lion.yeominrak.MainActivity
import kr.co.lion.yeominrak.R
import kr.co.lion.yeominrak.databinding.FragmentMainBinding
import kr.co.lion.yeominrak.databinding.RowMainBinding


class MainFragment : Fragment() {
    lateinit var binding:FragmentMainBinding
    lateinit var imageRes:MutableList<Int>
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(inflater)

        initData()
        initView()
        setToolbar()

        setEvent()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarMain.apply{
                title = "메인 화면"
                inflateMenu(R.menu.menu_main)
            }
        }
    }


    fun initData(){
        mainActivity = activity as MainActivity

        // 테스트 이미지
        imageRes = mutableListOf(
            R.drawable.test_img_1, R.drawable.test_img_2, R.drawable.test_img_3,
            R.drawable.test_img_4, R.drawable.test_img_5, R.drawable.test_img_6,
            R.drawable.test_img_7
        )
    }

    fun initView(){

    }

    fun setEvent(){
        binding.apply{
            // RecyclerView 셋팅
            recyclerViewMain.apply{
                // 어댑터
                adapter = RecyclerViewAdapterMain()
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }

            textViewBoardMain.setOnClickListener {
                mainActivity.replaceFragment(FragmentNameMain.BOARD_FRAGMENT,true,true, null)
            }

            textViewWeekBoardMain.setOnClickListener {
                mainActivity.replaceFragment(FragmentNameMain.WEEK_BOARD_FRAGMENT,true,true,null)
            }

            textViewCheckAttendanceMain.setOnClickListener{
                mainActivity.replaceFragment(FragmentNameMain.CHECK_ATTENDANCE_FRAGMENT,true,true,null)
            }



        }
    }

    inner class RecyclerViewAdapterMain: RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolderMain>() {
        inner class ViewHolderMain(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding: RowMainBinding

            init{
                this.rowMainBinding = rowMainBinding

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMain {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val viewHolderMain = ViewHolderMain(rowMainBinding)
            return viewHolderMain
        }

        override fun onBindViewHolder(holder: ViewHolderMain, position: Int) {
            holder.rowMainBinding.imageViewCarousel.setImageResource(imageRes[position])
        }

        override fun getItemCount(): Int = imageRes.size

    }
}