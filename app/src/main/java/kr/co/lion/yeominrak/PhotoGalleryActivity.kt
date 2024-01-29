package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html.ImageGetter
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.carousel.CarouselLayoutManager
import kr.co.lion.yeominrak.databinding.ActivityBulletinBoardBinding
import kr.co.lion.yeominrak.databinding.ActivityPhotoGalleryBinding
import kr.co.lion.yeominrak.databinding.GalleryItemBinding

class PhotoGalleryActivity : AppCompatActivity() {
    lateinit var binding:ActivityPhotoGalleryBinding
    lateinit var imageRes:MutableList<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setEvent()
    }

    fun initData(){
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
            galleryRecyclerView.apply{
                // 어댑터
                adapter = RecyclerViewAdapter()
                // 레이아웃 매니저
                layoutManager = CarouselLayoutManager()
                // layoutManager = CarouselLayoutManager(MultiBrowseCarouselStrategy())
                // layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
                // layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
            }
        }
    }

    inner class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(galleryItemBinding: GalleryItemBinding): RecyclerView.ViewHolder(galleryItemBinding.root){
            val galleryItemBinding:GalleryItemBinding

            init{
                this.galleryItemBinding = galleryItemBinding

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val galleryItemBinding = GalleryItemBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(galleryItemBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int = imageRes.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.galleryItemBinding.imageViewCarousel.setImageResource(imageRes[position])
        }
    }
}