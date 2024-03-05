package kr.co.lion.yeominrak.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import kr.co.lion.yeominrak.R


class SettingFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)
    }

}