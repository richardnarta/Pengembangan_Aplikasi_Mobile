package com.example.mycounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val counterFragment = CounterFragment()

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container,counterFragment, CounterFragment::class.java.simpleName)
            .commit()
    }
}