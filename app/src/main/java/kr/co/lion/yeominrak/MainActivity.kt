package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.yeominrak.databinding.ActivityMainBinding
import kr.co.lion.yeominrak.databinding.RowMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var imageRes:MutableList<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initData()
        initView()
        setEvent()
    }

    fun initData(){
        // 테스트 이미지
        imageRes = mutableListOf(
            R.drawable.image_1,R.drawable.image_2,R.drawable.image_3,
            R.drawable.image_4,R.drawable.image_5,R.drawable.image_6,
            R.drawable.image_7,R.drawable.image_8,R.drawable.image_9,
            R.drawable.image_10
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
        }
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