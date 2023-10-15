package com.example.tugas5.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.example.tugas5.R
import com.example.tugas5.data.LoginCondition
import com.example.tugas5.data.LoginData
import com.example.tugas5.data.SplashCondition
import com.example.tugas5.databinding.FragmentLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private lateinit var etUsername: EditText
    private lateinit var etPass: EditText
    private lateinit var loginButton: Button
    private lateinit var registrationButton: TextView

    private lateinit var savedPass:String
    private lateinit var savedUsername: String

    companion object{
        lateinit var login: LoginData
        lateinit var isOpen: SplashCondition
        lateinit var isLogin: LoginCondition
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        etUsername = binding.etUsername
        etPass = binding.etPass
        loginButton = binding.btnLogin
        registrationButton = binding.register

        login = LoginData(requireContext())
        isOpen = SplashCondition(requireContext())
        isLogin = LoginCondition(requireContext())

        checkSplash()

        buttonLogin()

        checkLogin()

        buttonRegistration()

        return root
    }

    private fun buttonLogin() {
        login.userNameFlow.asLiveData().observe(viewLifecycleOwner) {
            savedUsername = it.toString()
        }

        login.userPassFlow.asLiveData().observe(viewLifecycleOwner) {
            savedPass = it.toString()
        }

        loginButton.setOnClickListener {
            if (etUsername.text.toString() == savedUsername && etPass.text.toString() == savedPass){
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavHome(savedUsername))
                isLogin.isLoginFlow.asLiveData().observe(viewLifecycleOwner){
                    GlobalScope.launch {
                        isLogin.storeSession(true)
                    }
                }
            }else{
                Toast.makeText(activity,"Username / Password Invalid!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buttonRegistration(){
        registrationButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_login_to_nav_registration)
        }
    }
    private fun checkSplash(){
        isOpen.isOpenFlow.asLiveData().observe(viewLifecycleOwner){
            if (it == false){
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavSplash())
            }
        }
    }

    private fun checkLogin(){
        isLogin.isLoginFlow.asLiveData().observe(viewLifecycleOwner){ it -> "IS_LOGIN"
            if (it == true){
                login.userNameFlow.asLiveData().observe(viewLifecycleOwner) {
                    savedUsername = it.toString()
                }
                findNavController().navigate(LoginFragmentDirections.actionNavLoginToNavHome(savedUsername))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}