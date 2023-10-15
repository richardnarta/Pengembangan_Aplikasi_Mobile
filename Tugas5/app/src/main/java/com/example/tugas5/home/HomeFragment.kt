package com.example.tugas5.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tugas5.R
import com.example.tugas5.databinding.FragmentHomeBinding
import com.example.tugas5.login.LoginFragment
import com.example.tugas5.splash_screen.SplashFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding?=null
    private val binding get() = _binding!!

    private val args : HomeFragmentArgs by navArgs()
    private lateinit var logOutButton:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        logOutButton = binding.btnLogout

        buttonLogout()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments!=null){
            val name = args.username
            binding.username.text = getString(R.string.home_fragment,name)
        }
    }

    private fun buttonLogout(){
        logOutButton.setOnClickListener {
            GlobalScope.launch {
                LoginFragment.isLogin.storeSession(false)
            }
            findNavController().navigate(HomeFragmentDirections.actionNavHomeToNavLogin2())
        }
    }

}