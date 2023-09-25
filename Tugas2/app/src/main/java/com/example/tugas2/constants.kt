package com.example.tugas2

object Constants {
    fun getSkillData():ArrayList<Skill>{
        val skillList=ArrayList<Skill>()
        val skill1= Skill("Python")
        skillList.add(skill1)
        val skill2= Skill("C++")
        skillList.add(skill2)
        val skill3= Skill("Javascript")
        skillList.add(skill3)
        val skill4= Skill("Kotlin")
        skillList.add(skill4)

        return skillList
    }
}