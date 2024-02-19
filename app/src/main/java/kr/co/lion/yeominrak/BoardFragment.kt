package kr.co.lion.yeominrak

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.FragmentBoardBinding
import kr.co.lion.yeominrak.databinding.RowBoardBinding


class BoardFragment : Fragment() {
    lateinit var binding:FragmentBoardBinding
    lateinit var mainActivity: MainActivity
    lateinit var recyclerViewAdapter: RecyclerViewAdapterBoard
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentBoardBinding.inflate(inflater)
        // Inflate the layout for this fragment

        initData()
        initView()

        setToolbar()
        setEvent()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerViewAdapter.notifyDataSetChanged()
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
                    mainActivity.removeFragment(FragmentName.BOARD_FRAGMENT)
                }
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.menu_item_write_board -> {
                            mainActivity.replaceFragment(FragmentName.WRITE_BOARD_FRAGMENT,true,true,null)
                        }
                    }
                    true
                }
            }
        }
    }

    fun initData(){
        mainActivity = activity as MainActivity
    }
    fun initView(){
        recyclerViewAdapter = RecyclerViewAdapterBoard()

        binding.apply {


            recyclerViewBoard.adapter = recyclerViewAdapter
            recyclerViewBoard.layoutManager = LinearLayoutManager(requireContext())

            val decoration = MaterialDividerItemDecoration(requireContext(),
                MaterialDividerItemDecoration.VERTICAL)
            recyclerViewBoard.addItemDecoration(decoration)
        }
    }
    fun setEvent(){

    }

    inner class RecyclerViewAdapterBoard: RecyclerView.Adapter<RecyclerViewAdapterBoard.ViewHolderBoard>() {

        inner class ViewHolderBoard(rowBoardBinding: RowBoardBinding) : RecyclerView.ViewHolder(rowBoardBinding.root) {
            val rowBoardBinding: RowBoardBinding

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
            holder.rowBoardBinding.textViewTitle.text = Util.postList[position].postTitle
            holder.rowBoardBinding.textViewContent.text = Util.postList[position].postContent
            holder.rowBoardBinding.textViewNickname.text = Util.postList[position].postUserId
            holder.rowBoardBinding.textViewDate.text = Util.postList[position].postDate

            holder.rowBoardBinding.root.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("position",position)
                mainActivity.replaceFragment(FragmentName.SHOW_ONE_BOARD_FRAGMENT,true,true,bundle)
            }
        }
    }
}