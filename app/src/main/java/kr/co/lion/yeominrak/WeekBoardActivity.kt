package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.ActivityWeekBoardBinding
import kr.co.lion.yeominrak.databinding.RowBoardBinding

class WeekBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityWeekBoardBinding
    lateinit var userWeek:Week
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityWeekBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setToolbar()
        setEvent()

    }
    fun setToolbar(){
        binding.apply{
            toolbarWeekBoard.apply{

                title = "${Util.testUser.userWeek.str}요반 게시판"
                inflateMenu(R.menu.menu_week_board)
            }
        }
    }

    // ---------------------------------------------------------------

    fun initData(){
        userWeek = Util.testUser.userWeek
        Util.postList.forEach{
            if(it.week == userWeek){
                Util.myWeekList.add(it)
            }
        }
    }

    fun initView(){
        binding.apply{
            recyclerViewWeekBoard.apply{
                adapter = RecyclerViewAdapterWeekBoard(Util.myWeekList)
                layoutManager = LinearLayoutManager(this@WeekBoardActivity)

                val decoration = MaterialDividerItemDecoration(this@WeekBoardActivity,MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(decoration)
            }

        }
    }

    fun setEvent(){

    }

    inner class RecyclerViewAdapterWeekBoard(var itemList:MutableList<Post>):
        RecyclerView.Adapter<RecyclerViewAdapterWeekBoard.ViewHolderWeekBoard>() {
        inner class ViewHolderWeekBoard(rowBoardBinding: RowBoardBinding): RecyclerView.ViewHolder(rowBoardBinding.root) {
            val rowBoardBinding:RowBoardBinding
            init{
                this.rowBoardBinding = rowBoardBinding

                this.rowBoardBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                this.rowBoardBinding.root.setOnClickListener {
                    // recyclerView 한 칸에 대한 클릭 리스너

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderWeekBoard {
            val rowBoardBinding:RowBoardBinding = RowBoardBinding.inflate(layoutInflater)
            val viewHolderWeekBoard = ViewHolderWeekBoard(rowBoardBinding)
            return viewHolderWeekBoard
        }

        override fun getItemCount(): Int = itemList.size

        override fun onBindViewHolder(holder: ViewHolderWeekBoard, position: Int) {
            holder.rowBoardBinding.textViewTitle.text = itemList[position].title
            holder.rowBoardBinding.textViewContent.text = itemList[position].content
            holder.rowBoardBinding.textViewNickname.text = itemList[position].nickname
            holder.rowBoardBinding.textViewDate.text = itemList[position].date
        }
    }
}