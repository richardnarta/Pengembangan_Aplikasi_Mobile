package com.example.tugas5.splash_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.asLiveData
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.tugas5.R
import com.example.tugas5.data.LoginCondition
import com.example.tugas5.databinding.FragmentLoginBinding
import com.example.tugas5.databinding.FragmentSplashBinding
import com.example.tugas5.helper.Constant
import com.example.tugas5.helper.Splash
import com.example.tugas5.login.LoginFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SplashFragment : Fragment() {

    private var _binding: FragmentSplashBinding?=null
    private val binding get() = _binding!!

    private lateinit var image: ImageView
    private lateinit var text: TextView
    private lateinit var widget: TextView
    private lateinit var buttonNext: Button
    private lateinit var buttonSkip: Button
    private var counter by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val root: View = binding.root

        buttonSkip = binding.btnSkip
        buttonNext = binding.btnNext
        image = binding.image
        text = binding.splashMessage
        widget = binding.splashWidget

        counter = 0

        nextButton()

        skipButton()

        return root
    }

    private fun nextButton(){
        val assetList:ArrayList<Splash> = Constant.getSplashAssets(this)
        image.setImageDrawable(ResourcesCompat.getDrawable(image.resources, assetList[counter].image, null))
        text.text = assetList[counter].text
        widget.text = assetList[counter].widget

        buttonNext.setOnClickListener {
            counter+=1
            if (counter > 2){
                findNavController().navigate(R.id.action_nav_splash_to_nav_login)
                GlobalScope.launch {
                    LoginFragment.isOpen.storeSession(true)
                }
            }else{
                if (counter == 2){
                    buttonNext.text = getString(R.string.get_started)
                }
                text.text = assetList[counter].text
                widget.text = assetList[counter].widget
                image.setImageDrawable(ResourcesCompat.getDrawable(image.resources, assetList[counter].image, null))
            }
        }
    }

    private fun skipButton(){
        buttonSkip.setOnClickListener{
            LoginFragment.isOpen.isOpenFlow.asLiveData().observe(viewLifecycleOwner){
                if(it == false){
                    findNavController().navigate(R.id.action_nav_splash_to_nav_login)
                    GlobalScope.launch {
                        LoginFragment.isOpen.storeSession(true)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}