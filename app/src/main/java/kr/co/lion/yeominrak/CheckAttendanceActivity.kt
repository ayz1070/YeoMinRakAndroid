package kr.co.lion.yeominrak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.lion.yeominrak.databinding.ActivityCheckAttendanceBinding

class CheckAttendanceActivity : AppCompatActivity() {
    lateinit var binding:ActivityCheckAttendanceBinding
    lateinit var attendanceList:MutableList<Attendance>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        initView()
        setToolbar()
        setEvent()

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
    }

    fun initView(){

    }

    fun setEvent(){
        binding.apply{
            buttonAttendance.setOnClickListener {
                when(radioGroupAttendance.checkedRadioButtonId){
                    R.id.radioButtonNotSelected -> {
                        val attendance = Attendance(Util.testUserModel,AttendState.NOT_SELECTED)
                        attendanceList.add(attendance)
                        textViewTest.setText("${attendance.userModel.userName} ${attendance.isAttend.str}")
                    }
                    R.id.radioButtonAttend -> {
                        val attendance = Attendance(Util.testUserModel,AttendState.ATTEND)
                        attendanceList.add(attendance)
                        textViewTest.setText("${attendance.userModel.userName} ${attendance.isAttend.str}")
                    }
                    R.id.radioButtonNotAttend -> {
                        val attendance = Attendance(Util.testUserModel,AttendState.NOT_ATTEND)
                        attendanceList.add(attendance)
                        textViewTest.setText("${attendance.userModel.userName} ${attendance.isAttend.str}")
                    }
                    else -> "선택해주세요"
                }
            }
        }
    }
}