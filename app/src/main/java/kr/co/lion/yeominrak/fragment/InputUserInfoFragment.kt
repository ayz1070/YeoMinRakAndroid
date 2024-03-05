package kr.co.lion.yeominrak.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import kr.co.lion.yeominrak.LoginActivity
import kr.co.lion.yeominrak.MainActivity
import kr.co.lion.yeominrak.R
import kr.co.lion.yeominrak.UserDao
import kr.co.lion.yeominrak.Util
import kr.co.lion.yeominrak.databinding.FragmentInputUserInfoBinding
import kr.co.lion.yeominrak.model.UserModel
import kr.co.lion.yeominrak.model.Week


class InputUserInfoFragment : Fragment() {
    lateinit var binding:FragmentInputUserInfoBinding
    lateinit var loginActivity: LoginActivity

    lateinit var galleryResultLauncher: ActivityResultLauncher<Intent>

    lateinit var userName:String
    lateinit var userNickname:String
    lateinit var userWeek: Week
    lateinit var userProfileImage:ByteArray

    lateinit var imageUri: Uri
    lateinit var imageBitmap: Bitmap



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentInputUserInfoBinding.inflate(inflater)

        initData()
        initView()
        setEvent()

        return binding.root
    }

    fun processInput(){
        binding.apply{
            userName = editTextName.text.toString()
            userNickname = editTextId.text.toString()
            val userWeekStr = textViewMenuInputUser.text.toString()

            if(userName.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!", Snackbar.LENGTH_SHORT).show()
                return
            }

            if(userNickname.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!", Snackbar.LENGTH_SHORT).show()
                return
            }

            if(userWeekStr.isEmpty()){
                Snackbar.make(binding.root,"빈 칸이 존재합니다!!", Snackbar.LENGTH_SHORT).show()
                return
            }

            userWeek = Util.stringToWeek(userWeekStr)
            userProfileImage = Util.convertBitmapToByteArray(imageBitmap)

            val userModel = UserModel(0,userName, userNickname, userWeek, userProfileImage)
            UserDao.insertUser(loginActivity,userModel)
        }

    }

    fun initData(){
        val contract1 = ActivityResultContracts.StartActivityForResult()
        loginActivity = activity as LoginActivity

        galleryResultLauncher = registerForActivityResult(contract1){
            if(it.resultCode == AppCompatActivity.RESULT_OK){
                if(it.data != null){
                    imageUri = it.data?.data!!
                    imageBitmap = BitmapFactory.decodeStream(loginActivity.contentResolver.openInputStream(imageUri))
                    binding.imageViewProfileInputUser.setImageBitmap(imageBitmap)
                }
            }
        }
    }

    fun initView(){
        binding.apply{
            val weekArray = arrayOf(
                Week.WEEK_SUNDAY.str,
                Week.WEEK_MONDAY.str,
                Week.WEEK_TUESDAY.str,
                Week.WEEK_WEDNESDAY.str,
                Week.WEEK_THURSDAY.str,
                Week.WEEK_FRIDAY.str,
                Week.WEEK_SATURDAY.str
            )
            (textInputLayoutMenuInputUser.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(weekArray)
        }
    }

    fun setEvent(){
        binding.apply{
            buttonCompleteInputUser.setOnClickListener {
                processInput()
                val mainIntent = Intent(loginActivity, MainActivity::class.java)
                startActivity(mainIntent)
            }

            imageViewProfileInputUser.setOnClickListener {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                galleryResultLauncher.launch(galleryIntent)
            }
        }
    }
}