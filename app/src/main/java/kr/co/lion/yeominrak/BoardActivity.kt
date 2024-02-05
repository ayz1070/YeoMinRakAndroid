package kr.co.lion.yeominrak

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.ActivityBoardBinding
import kr.co.lion.yeominrak.databinding.RowBoardBinding


class BoardActivity : AppCompatActivity() {
    lateinit var binding: ActivityBoardBinding
    lateinit var postList: MutableList<Post>
    lateinit var recyclerViewAdapter:RecyclerViewAdapterBoard
    lateinit var writeBoardLauncher:ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        setLauncher()
        initData()
        initView()
        setEvent()
    }

    fun setToolbar(){
        binding.apply{
            toolbarBoard.apply{
                // 타이틀
                title = "자유 게시판"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(R.drawable.arrow_back_24px)
                inflateMenu(R.menu.menu_board)
                setNavigationOnClickListener {
                    setResult(RESULT_OK)
                    finish()
                }
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_write_board -> {
                            val writeIntent = Intent(this@BoardActivity,WriteBoardActivity::class.java)
                            writeBoardLauncher.launch(writeIntent)
                        }
                    }
                    true
                }
            }
        }
    }

    fun setLauncher(){
        val contract1 = ActivityResultContracts.StartActivityForResult()
        writeBoardLauncher = registerForActivityResult(contract1){
            // 다시 되돌아 올 때의 코드 구현
            if(it.resultCode == RESULT_OK){
                if(it.data != null){
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                        val postData = it.data?.getParcelableExtra("postData",Post::class.java)!!
                        postList.add(postData)
                    }else{
                        val postData = it.data?.getParcelableExtra<Post>("postData")!!
                        postList.add(postData)
                    }
                    binding.recyclerViewBoard.adapter?.notifyDataSetChanged()
                }
            }
        }


    }

    fun initData(){
        postList = mutableListOf()

    }
    fun initView(){
        recyclerViewAdapter = RecyclerViewAdapterBoard()

        binding.apply {


            recyclerViewBoard.adapter = recyclerViewAdapter
            recyclerViewBoard.layoutManager = LinearLayoutManager(this@BoardActivity)

            val decoration = MaterialDividerItemDecoration(this@BoardActivity,MaterialDividerItemDecoration.VERTICAL)
            recyclerViewBoard.addItemDecoration(decoration)
        }
    }
    fun setEvent(){

    }

    inner class RecyclerViewAdapterBoard: RecyclerView.Adapter<RecyclerViewAdapterBoard.ViewHolderBoard>() {

        inner class ViewHolderBoard(rowBoardBinding: RowBoardBinding) : RecyclerView.ViewHolder(rowBoardBinding.root) {
            val rowBoardBinding:RowBoardBinding

            init {
                this.rowBoardBinding = rowBoardBinding

                this.rowBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                this.rowBoardBinding.root.setOnClickListener {
                    // 리사이클러뷰 한 칸에 대한 클릭 리스너
                }

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBoard {
            val boardItemBinding = RowBoardBinding.inflate(layoutInflater)
            val viewHolderBoard = ViewHolderBoard(boardItemBinding)
            return viewHolderBoard
        }

        override fun getItemCount(): Int = postList.size

        override fun onBindViewHolder(holder: ViewHolderBoard, position: Int) {
            holder.rowBoardBinding.textViewTitle.text = postList[position].title
            holder.rowBoardBinding.textViewContent.text = postList[position].content
            holder.rowBoardBinding.textViewNickname.text = postList[position].nickname
            holder.rowBoardBinding.textViewDate.text = postList[position].date
        }
    }
}