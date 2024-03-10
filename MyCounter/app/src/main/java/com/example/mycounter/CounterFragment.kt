package com.example.mycounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class CounterFragment : Fragment(), View.OnClickListener {

    private lateinit var buttonPlus:Button
    private lateinit var tvDisplay: TextView
    private lateinit var buttonMinus:Button

    private var counter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonPlus = view.findViewById(R.id.btn_plus)
        tvDisplay = view.findViewById(R.id.tv_counter)
        buttonMinus = view.findViewById(R.id.btn_minus)

        updateCounter(null)

        buttonPlus.setOnClickListener(this)
        buttonMinus.setOnClickListener(this)
    }

    private fun updateCounter(type: String?) {
        if (type == "plus"){
            counter+=1
        }else if (type == "minus"){
            counter-=1
        }

        tvDisplay.text = counter.toString()
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_plus){
            updateCounter("plus")
        }else if (v?.id == R.id.btn_minus){
            updateCounter("minus")
        }
    }
}