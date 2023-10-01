package com.example.tugas3

object Constants {
    fun getSkillData():ArrayList<Skill>{
        val skillList=ArrayList<Skill>()
        val skill1= Skill("Python", R.drawable.python)
        skillList.add(skill1)
        val skill2= Skill("C++", R.drawable.cplusplus)
        skillList.add(skill2)
        val skill3= Skill("Javascript", R.drawable.js)
        skillList.add(skill3)
        val skill4= Skill("Kotlin", R.drawable.kotlin)
        skillList.add(skill4)
        val skill5= Skill("Ruby", R.drawable.ruby)
        skillList.add(skill5)
        val skill6= Skill("Java", R.drawable.java)
        skillList.add(skill6)
        val skill7= Skill("Swift", R.drawable.swift)
        skillList.add(skill7)
        val skill8= Skill("C#", R.drawable.csharp)
        skillList.add(skill8)
        val skill9= Skill("Typescript", R.drawable.ts)
        skillList.add(skill9)
        val skill10= Skill("PHP", R.drawable.php)
        skillList.add(skill10)

        return skillList
    }
}