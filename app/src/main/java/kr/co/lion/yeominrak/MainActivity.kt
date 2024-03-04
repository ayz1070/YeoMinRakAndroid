package kr.co.lion.yeominrak

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.yeominrak.databinding.ActivityMainBinding
import kr.co.lion.yeominrak.databinding.RowMainBinding
import kr.co.lion.yeominrak.model.UserModel

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null

    lateinit var myUserModel : UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        installSplashScreen()
        setContentView(binding.root)
        initData()

        replaceFragment(FragmentName.MAIN_FRAGMENT, false,false,null)
    }

    fun initData(){
        myUserModel = UserDao.selectOneUser(this,1)
    }

    fun replaceFragment(fragmentName:FragmentName,addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(fragmentName){
            FragmentName.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            FragmentName.BOARD_FRAGMENT -> {
                newFragment = BoardFragment()
            }
            FragmentName.WEEK_BOARD_FRAGMENT -> {
                newFragment = WeekBoardFragment()
            }
            FragmentName.CHECK_ATTENDANCE_FRAGMENT -> {
                newFragment = CheckAttendanceFragment()
            }
            FragmentName.WRITE_BOARD_FRAGMENT -> {
                newFragment = WriteBoardFragment()
            }
            FragmentName.MODIFY_BOARD_FRAGMENT -> {
                newFragment = ModifyBoardFragment()
            }
            FragmentName.SHOW_ONE_BOARD_FRAGMENT -> {
                newFragment = ShowOneBoardFragment()
            }
            FragmentName.SETTING_FRAGMENT -> {
                newFragment = SettingFragment()
            }

        }

        if(data != null){
            newFragment?.arguments = data
        }

        if(newFragment != null){

            // 애니메이션 설정
            if(isAnimate){
                if(oldFragment != null){
                    // old에서 new가 새롭게 보여질 때 old의 애니메이션
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X,true)
                    // new에서 old로 되돌아갈때 old의 애니메이션
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X,false)

                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                // old에서 new가 새롭게 보여질 때 new의 애니메이션
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X,true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X,false)

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null

                // Fragment 교체
                fragmentTransaction.replace(R.id.fragmentContainerMain, newFragment!!)

                // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시킨다.
                if(addToBackStack){
                    fragmentTransaction.addToBackStack(fragmentName.str)
                }
                // Fragment 교체를 확정한다.
                fragmentTransaction.commit()
            }
        }

    }

    fun removeFragment(fragmentName:FragmentName){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

enum class FragmentName(var str:String){
    MAIN_FRAGMENT("MainFragment"),
    BOARD_FRAGMENT("BoardFragment"),
    WEEK_BOARD_FRAGMENT("WeekBoardFragment"),
    CHECK_ATTENDANCE_FRAGMENT("CheckAttendanceFragment"),
    WRITE_BOARD_FRAGMENT("WriteBoardFragment"),
    SHOW_ONE_BOARD_FRAGMENT("ShowOneBoardFragment"),
    MODIFY_BOARD_FRAGMENT("ModifyBoardFragment"),
    SETTING_FRAGMENT("SettingFragment")
}