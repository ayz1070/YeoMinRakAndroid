package kr.co.lion.yeominrak

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.ActivityBulletinBoardBinding
import kr.co.lion.yeominrak.databinding.RowBoardBinding


class BulletinBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityBulletinBoardBinding
    lateinit var postList: MutableList<Post>
    lateinit var recyclerViewAdapter:RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBulletinBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setEvent()
    }

    fun initData(){
        postList = mutableListOf()
        postList.add(Post("제목1","내용1","닉네임1","날짜1"))
        postList.add(Post("제목2","내용2","닉네임2","날짜2"))
        postList.add(Post("제목3","내용3","닉네임3","날짜3"))
    }
    fun initView(){
        recyclerViewAdapter = RecyclerViewAdapter()

        binding.apply {
            toolbarBulletinBoard.apply{
                title = "자유게시판"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.board_menu)
            }

            boardRecyclerView.adapter = recyclerViewAdapter
            boardRecyclerView.layoutManager = LinearLayoutManager(this@BulletinBoardActivity)

            val decoration = MaterialDividerItemDecoration(this@BulletinBoardActivity,MaterialDividerItemDecoration.VERTICAL)
            boardRecyclerView.addItemDecoration(decoration)
        }
    }
    fun setEvent(){

    }

    inner class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderClass>() {

        inner class ViewHolderClass(rowBoardBinding: RowBoardBinding) : RecyclerView.ViewHolder(rowBoardBinding.root) {
            val rowBoardBinding:RowBoardBinding

            init {
                this.rowBoardBinding = rowBoardBinding

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val boardItemBinding = RowBoardBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(boardItemBinding)
            return viewHolderClass
        }

        override fun getItemCount(): Int = postList.size

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.rowBoardBinding.textViewTitle.text = postList[position].title
            holder.rowBoardBinding.textViewContent.text = postList[position].content
            holder.rowBoardBinding.textViewNickname.text = postList[position].nickname
            holder.rowBoardBinding.textViewDate.text = postList[position].date
        }
    }
}