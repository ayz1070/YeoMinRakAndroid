package kr.co.lion.yeominrak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.FragmentWeekBoardBinding
import kr.co.lion.yeominrak.databinding.RowBoardBinding
import kr.co.lion.yeominrak.model.Post
import kr.co.lion.yeominrak.model.Week


class WeekBoardFragment : Fragment() {
    lateinit var binding:FragmentWeekBoardBinding
    lateinit var userWeek: Week
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWeekBoardBinding.inflate(inflater)

        //initData()
        initView()
        setToolbar()
        setEvent()

        // Inflate the layout for this fragment
        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarWeekBoard.apply{

                //title = "${Util.testUserModel.userWeek.str}요반 게시판"
                inflateMenu(R.menu.menu_week_board)
            }
        }
    }

    // ---------------------------------------------------------------

//    fun initData(){
//        userWeek = Util.testUserModel.userWeek
//        Util.postList.forEach{
//            if(it.postWeek == userWeek){
//                Util.myWeekList.add(it)
//            }
//        }
//    }

    fun initView(){
        binding.apply{
            recyclerViewWeekBoard.apply{
                //adapter = RecyclerViewAdapterWeekBoard(Util.myWeekList)
                layoutManager = LinearLayoutManager(requireContext())

                val decoration = MaterialDividerItemDecoration(requireContext(),
                    MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(decoration)
            }

        }
    }

    fun setEvent(){

    }

    inner class RecyclerViewAdapterWeekBoard(var itemList:MutableList<Post>):
        RecyclerView.Adapter<RecyclerViewAdapterWeekBoard.ViewHolderWeekBoard>() {
        inner class ViewHolderWeekBoard(rowBoardBinding: RowBoardBinding): RecyclerView.ViewHolder(rowBoardBinding.root) {
            val rowBoardBinding: RowBoardBinding
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
            val rowBoardBinding: RowBoardBinding = RowBoardBinding.inflate(layoutInflater)
            val viewHolderWeekBoard = ViewHolderWeekBoard(rowBoardBinding)
            return viewHolderWeekBoard
        }

        override fun getItemCount(): Int = itemList.size

        override fun onBindViewHolder(holder: ViewHolderWeekBoard, position: Int) {
            holder.rowBoardBinding.textViewTitle.text = itemList[position].postTitle
            holder.rowBoardBinding.textViewContent.text = itemList[position].postContent
            holder.rowBoardBinding.textViewNickname.text = itemList[position].postUserNickname
            holder.rowBoardBinding.textViewDate.text = itemList[position].postDate
        }
    }

}