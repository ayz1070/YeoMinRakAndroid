package kr.co.lion.yeominrak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.FragmentCheckAttendanceBinding
import kr.co.lion.yeominrak.databinding.RowCheckBinding

class CheckAttendanceFragment : Fragment() {
    lateinit var binding:FragmentCheckAttendanceBinding
    lateinit var attendanceList:MutableList<Attendance>
    lateinit var mainActivity: MainActivity
    lateinit var attendList:MutableList<Attendance>
    lateinit var notAttendList:MutableList<Attendance>
    lateinit var notSelectedList:MutableList<Attendance>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckAttendanceBinding.inflate(inflater)

        initData()
        initView()
        setToolbar()
        setEvent()

        return binding.root
    }

    fun setToolbar(){
        binding.apply{
            toolbarCheckAttendance.apply{
                title = "${Util.getCurrentDayOfWeek()} 출석체크"

                setNavigationIcon(R.drawable.arrow_back_24px)

            }
        }
    }

    fun initData(){
        attendanceList = mutableListOf()
        attendList = mutableListOf()
        notAttendList = mutableListOf()
        notSelectedList = mutableListOf()

        mainActivity = activity as MainActivity
    }

    fun initView(){
        binding.apply{
            recyclerViewCheckAttendanceAttend.apply{
                adapter = RecyclerViewAdapterCheckAttend()
                layoutManager = LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false)
            }
            recyclerViewCheckAttendanceNotAttend.apply{
                adapter = RecyclerViewAdapterCheckNotAttend()
                layoutManager = LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false)
            }
            recyclerViewCheckAttendanceNotSelected.apply{
                adapter = RecyclerViewAdapterCheckNotSelected()
                layoutManager = LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,false)
            }

        }
    }

    fun setEvent(){
        binding.apply{
            buttonAttendance.setOnClickListener {
                when(radioGroupAttendance.checkedRadioButtonId){
                    R.id.radioButtonNotSelected -> {
                        val attendance = Attendance(Util.testUserModel.userName,AttendState.NOT_SELECTED)
                        attendanceList.add(attendance)
                        notSelectedList.add(attendance)

                    }
                    R.id.radioButtonAttend -> {
                        val attendance = Attendance(Util.testUserModel.userName,AttendState.ATTEND)
                        attendanceList.add(attendance)
                        attendList.add(attendance)

                    }
                    R.id.radioButtonNotAttend -> {
                        val attendance = Attendance(Util.testUserModel.userName,AttendState.NOT_ATTEND)
                        attendanceList.add(attendance)
                        notAttendList.add(attendance)
                    }
                    else -> "선택해주세요"
                }
                recyclerViewCheckAttendanceAttend.adapter?.notifyDataSetChanged()
                recyclerViewCheckAttendanceNotAttend.adapter?.notifyDataSetChanged()
                recyclerViewCheckAttendanceNotSelected.adapter?.notifyDataSetChanged()
            }
        }
    }


    //------------------------------------------------------------------------------------------
    // 출석 리사이클러뷰
    inner class RecyclerViewAdapterCheckAttend():
        RecyclerView.Adapter<RecyclerViewAdapterCheckAttend.ViewHolderCheckAttend>() {

        inner class ViewHolderCheckAttend(rowCheckBinding: RowCheckBinding):RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckAttend {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheck = ViewHolderCheckAttend(rowCheckBinding)
            return viewHolderCheck
        }

        override fun getItemCount(): Int = attendList.size

        override fun onBindViewHolder(holder: ViewHolderCheckAttend, position: Int) {
            // 이미지는 테스트용
            holder.rowCheckBinding.imageViewProfileCheckRow.setImageResource(R.drawable.image_2)
            holder.rowCheckBinding.textViewNameCheckRow.text = attendList[position].userName
        }
    }

    //------------------------------------------------------------------------------------------
    // 결석 리사이클러뷰
    inner class RecyclerViewAdapterCheckNotAttend():
        RecyclerView.Adapter<RecyclerViewAdapterCheckNotAttend.ViewHolderCheckNotAttend>() {

        inner class ViewHolderCheckNotAttend(rowCheckBinding: RowCheckBinding):RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckNotAttend {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheckNotAttend = ViewHolderCheckNotAttend(rowCheckBinding)
            return viewHolderCheckNotAttend
        }

        override fun getItemCount(): Int = notAttendList.size

        override fun onBindViewHolder(holder: ViewHolderCheckNotAttend, position: Int) {
            // 이미지는 테스트용
            holder.rowCheckBinding.imageViewProfileCheckRow.setImageResource(R.drawable.image_2)
            holder.rowCheckBinding.textViewNameCheckRow.text = notAttendList[position].userName

        }
    }

    //------------------------------------------------------------------------------------------
    // 출석 리사이클러뷰
    inner class RecyclerViewAdapterCheckNotSelected():
        RecyclerView.Adapter<RecyclerViewAdapterCheckNotSelected.ViewHolderCheckNotSelected>() {

        inner class ViewHolderCheckNotSelected(rowCheckBinding: RowCheckBinding):RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheckNotSelected {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheckNotSelected = ViewHolderCheckNotSelected(rowCheckBinding)
            return viewHolderCheckNotSelected
        }

        override fun getItemCount(): Int = notSelectedList.size

        override fun onBindViewHolder(holder: ViewHolderCheckNotSelected, position: Int) {
            // 이미지는 테스트용
            holder.rowCheckBinding.imageViewProfileCheckRow.setImageResource(R.drawable.image_2)
            holder.rowCheckBinding.textViewNameCheckRow.text = notSelectedList[position].userName
        }
    }

}