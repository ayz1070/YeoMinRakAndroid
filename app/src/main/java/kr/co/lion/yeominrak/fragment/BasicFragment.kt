package kr.co.lion.yeominrak.fragment

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import kr.co.lion.yeominrak.FragmentNameMain
import kr.co.lion.yeominrak.LoginActivity
import kr.co.lion.yeominrak.MainActivity
import kr.co.lion.yeominrak.R
import kr.co.lion.yeominrak.databinding.FragmentBasicBinding



class BasicFragment : Fragment() {
    lateinit var binding:FragmentBasicBinding
    lateinit var mainActivity: MainActivity

    var oldFragment: Fragment? = null
    var newFragment: Fragment? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mainActivity = activity as MainActivity
        binding = FragmentBasicBinding.inflate(inflater)
        replaceFragment(FragmentNameMain.MAIN_FRAGMENT,false,false,null)
        setBottomNavigation()

        return binding.root
    }

    fun setBottomNavigation(){
        binding.apply{
            bottomNavigationBasic.apply{
                setOnItemSelectedListener {
                    when(it.itemId){
                        R.id.menu_item_home_main_bottom -> {
                            replaceFragment(FragmentNameMain.MAIN_FRAGMENT,true,true,null)
                        }
                        R.id.menu_item_check_main_bottom -> {
                            replaceFragment(FragmentNameMain.CHECK_ATTENDANCE_FRAGMENT,true,true,null)
                        }
                        R.id.menu_item_my_page_main_bottom -> {
                            replaceFragment(FragmentNameMain.SETTING_FRAGMENT,true,true,null)
                        }
                    }

                    true
                }

            }
        }
    }

    fun replaceFragment(fragmentName:FragmentNameMain,addToBackStack:Boolean, isAnimate:Boolean, data:Bundle?){
        SystemClock.sleep(200)

        // Fragment를 교체할 수 있는 객체를 추출한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // oldFragment에 newFragment가 가지고 있는 Fragment 객체를 담아준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 이름으로 분기한다.
        // Fragment의 객체를 생성하여 변수에 담아준다.
        when(fragmentName){
            FragmentNameMain.MAIN_FRAGMENT -> {
                newFragment = MainFragment()
            }
            FragmentNameMain.BOARD_FRAGMENT -> {
                newFragment = BoardFragment()
            }
            FragmentNameMain.WEEK_BOARD_FRAGMENT -> {
                newFragment = WeekBoardFragment()
            }
            FragmentNameMain.CHECK_ATTENDANCE_FRAGMENT -> {
                newFragment = CheckAttendanceFragment()
            }
            FragmentNameMain.WRITE_BOARD_FRAGMENT -> {
                newFragment = WriteBoardFragment()
            }
            FragmentNameMain.MODIFY_BOARD_FRAGMENT -> {
                newFragment = ModifyBoardFragment()
            }
            FragmentNameMain.SHOW_ONE_BOARD_FRAGMENT -> {
                newFragment = ShowOneBoardFragment()
            }
            FragmentNameMain.SETTING_FRAGMENT -> {
                newFragment = SettingFragment()
            }
            FragmentNameMain.BASIC_FRAGMENT -> {
                newFragment = BasicFragment()
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
                fragmentTransaction.replace(R.id.fragmentContainerBasic, newFragment!!)

                // addToBackStack 변수의 값이 true면 새롭게 보여질 Fragment를 BackStack에 포함시킨다.
                if(addToBackStack){
                    fragmentTransaction.addToBackStack(fragmentName.str)
                }
                // Fragment 교체를 확정한다.
                fragmentTransaction.commit()
            }
        }

    }

    fun removeFragment(fragmentName:FragmentNameMain){
        SystemClock.sleep(200)

        // 지정한 이름으로 있는 Fragment를 BackStack에서 제거한다.
        mainActivity.supportFragmentManager.popBackStack(fragmentName.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }




}