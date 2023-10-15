package com.example.tugas5.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tugas5.databinding.FragmentRegistrationBinding
import com.example.tugas5.login.LoginFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding?=null
    private val binding get() = _binding!!

    private lateinit var etUsername: EditText
    private lateinit var etPass: EditText
    private lateinit var etPass2: EditText
    private lateinit var registrationButton: Button
    private lateinit var loginButton: TextView

    private var userName = ""
    private var userPass = ""
    private var userPass2 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        etUsername = binding.etUsername
        etPass = binding.etPass
        etPass2 = binding.etPass2
        registrationButton = binding.btnRegister
        loginButton = binding.login

        buttonRegistration()

        buttonLogin()

        return root
    }

    private fun buttonRegistration() {
        registrationButton.setOnClickListener {
            userName = etUsername.text.toString()
            userPass = etPass.text.toString()
            userPass2 = etPass2.text.toString()

            if (userName!="" && userPass!="" && userPass==userPass2){
                GlobalScope.launch {
                    LoginFragment.login.storeUser(userName, userPass)
                }
                findNavController().navigate(RegistrationFragmentDirections.actionNavRegistrationToNavLogin())
                Toast.makeText(activity,"Registration success!!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(activity,"Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buttonLogin(){
        loginButton.setOnClickListener {
            findNavController().navigate(RegistrationFragmentDirections.actionNavRegistrationToNavLogin())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}