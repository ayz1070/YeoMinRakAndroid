package kr.co.lion.yeominrak

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import kr.co.lion.yeominrak.databinding.FragmentCheckAttendanceBinding
import kr.co.lion.yeominrak.databinding.RowCheckBinding

class CheckAttendanceFragment : Fragment() {
    lateinit var binding:FragmentCheckAttendanceBinding
    lateinit var attendanceList:MutableList<Attendance>
    lateinit var mainActivity: MainActivity
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
        mainActivity = activity as MainActivity
    }

    fun initView(){
        binding.apply{
            recyclerViewCheckAttendance.apply{
                adapter = RecyclerViewAdapterCheck()
                layoutManager = LinearLayoutManager(mainActivity)

                val deco = MaterialDividerItemDecoration(mainActivity, MaterialDividerItemDecoration.VERTICAL)
                addItemDecoration(deco)
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

                    }
                    R.id.radioButtonAttend -> {
                        val attendance = Attendance(Util.testUserModel.userName,AttendState.ATTEND)
                        attendanceList.add(attendance)

                    }
                    R.id.radioButtonNotAttend -> {
                        val attendance = Attendance(Util.testUserModel.userName,AttendState.NOT_ATTEND)
                        attendanceList.add(attendance)
                    }
                    else -> "선택해주세요"
                }
                recyclerViewCheckAttendance.adapter?.notifyDataSetChanged()

            }
        }
    }

    fun updateAttendanceList(attendance: Attendance){
        var idx = 0
        attendanceList.forEach{
            if(it.userName == attendance.userName){
                attendanceList[idx] = attendance
                idx += 1
            }
        }
        binding.recyclerViewCheckAttendance.adapter?.notifyDataSetChanged()
    }

    inner class RecyclerViewAdapterCheck():
        RecyclerView.Adapter<RecyclerViewAdapterCheck.ViewHolderCheck>() {

        inner class ViewHolderCheck(rowCheckBinding: RowCheckBinding):RecyclerView.ViewHolder(rowCheckBinding.root){
            val rowCheckBinding:RowCheckBinding

            init {
                this.rowCheckBinding = rowCheckBinding
                this.rowCheckBinding.root.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCheck {
            val rowCheckBinding = RowCheckBinding.inflate(layoutInflater)
            val viewHolderCheck = ViewHolderCheck(rowCheckBinding)
            return viewHolderCheck
        }

        override fun getItemCount(): Int = attendanceList.size

        override fun onBindViewHolder(holder: ViewHolderCheck, position: Int) {
            holder.rowCheckBinding.textViewNameCheckRow.text = attendanceList[position].userName
            holder.rowCheckBinding.textViewAttendanceCheckRow.text = attendanceList[position].isAttend.str
        }
    }

}