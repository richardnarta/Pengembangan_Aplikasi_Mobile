package com.example.tugas3.ui.utilities

import android.content.res.TypedArray
import androidx.fragment.app.Fragment
import com.example.tugas3.R
import com.example.tugas3.ui.skill.Skill

object Constants {
    fun getSkillData(fragment: Fragment):ArrayList<Skill>{
        val skillList=ArrayList<Skill>()

        val text:Array<String> = fragment.resources.getStringArray(R.array.language)
        val image:TypedArray = fragment.resources.obtainTypedArray(R.array.logo)
        val length = text.size - 1

        for (i in 0..length){
            skillList.add(Skill(text[i],image.getResourceId(i,-1)))
        }

        image.recycle()
        return skillList
    }
}