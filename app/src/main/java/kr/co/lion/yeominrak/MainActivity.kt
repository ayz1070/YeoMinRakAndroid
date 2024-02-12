package kr.co.lion.yeominrak

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.yeominrak.databinding.ActivityMainBinding
import kr.co.lion.yeominrak.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var imageRes:MutableList<Int>


    // 런처 객체
    lateinit var boardLauncher:ActivityResultLauncher<Intent>
    lateinit var weekBoardLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent 계약

        initData()
        initView()
        setToolbar()

        setEvent()

    }

    fun setToolbar(){
        binding.apply{
            toolbarMain.apply{
                title = "메인 화면"
                setTitleTextColor(getColor(R.color.white))
                inflateMenu(R.menu.menu_main)
            }
        }
    }


    fun initData(){
        // 테스트 이미지
        imageRes = mutableListOf(
            R.drawable.test_img_1,R.drawable.test_img_2,R.drawable.test_img_3,
            R.drawable.test_img_4,R.drawable.test_img_5,R.drawable.test_img_6,
            R.drawable.test_img_7
        )

        boardLauncher = registerForActivityResult(getContract()){
            // 계약 후 가져올 데이터에 대한 코드 작성

        }
        weekBoardLauncher = registerForActivityResult(getContract()){

        }
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
                val intent = Intent(this@MainActivity,BoardActivity::class.java)
                boardLauncher.launch(intent)
            }

            textViewWeekBoardMain.setOnClickListener {
                val weekBoardIntent = Intent(this@MainActivity,WeekBoardActivity::class.java)
                weekBoardLauncher.launch(weekBoardIntent)
            }
        }
    }

    fun getContract(): ActivityResultContracts.StartActivityForResult {
        return ActivityResultContracts.StartActivityForResult()
    }

    inner class RecyclerViewAdapterMain: RecyclerView.Adapter<RecyclerViewAdapterMain.ViewHolderMain>() {
        inner class ViewHolderMain(rowMainBinding: RowMainBinding): RecyclerView.ViewHolder(rowMainBinding.root){
            val rowMainBinding:RowMainBinding

            init{
                this.rowMainBinding = rowMainBinding

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerViewAdapterMain.ViewHolderMain {
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