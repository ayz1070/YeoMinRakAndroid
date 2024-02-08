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
    lateinit var recyclerViewAdapter:RecyclerViewAdapterBoard
    lateinit var writeBoardLauncher:ActivityResultLauncher<Intent>
    lateinit var showOneBoardLauncher: ActivityResultLauncher<Intent>

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

    override fun onResume() {
        super.onResume()
        binding.apply{
            recyclerViewBoard.adapter?.notifyDataSetChanged()
        }
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
            binding.recyclerViewBoard.adapter?.notifyDataSetChanged()
        }

        val contract2 = ActivityResultContracts.StartActivityForResult()
        showOneBoardLauncher = registerForActivityResult(contract2){

        }



    }

    fun initData(){


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



            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBoard {
            val boardItemBinding = RowBoardBinding.inflate(layoutInflater)
            val viewHolderBoard = ViewHolderBoard(boardItemBinding)
            return viewHolderBoard
        }

        override fun getItemCount(): Int = Util.postList.size

        override fun onBindViewHolder(holder: ViewHolderBoard, position: Int) {
            holder.rowBoardBinding.textViewTitle.text = Util.postList[position].title
            holder.rowBoardBinding.textViewContent.text = Util.postList[position].content
            holder.rowBoardBinding.textViewNickname.text = Util.postList[position].nickname
            holder.rowBoardBinding.textViewDate.text = Util.postList[position].date

            holder.rowBoardBinding.root.setOnClickListener {
                val showIntent = Intent(this@BoardActivity,ShowOneBoardActivity::class.java)
                showIntent.putExtra("position",position)
                showOneBoardLauncher.launch(showIntent)
            }
        }
    }
}