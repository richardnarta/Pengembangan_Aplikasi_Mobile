package com.example.tugas3.ui.skill

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas3.databinding.SkillListBinding
import com.example.tugas3.ui.utilities.Constants

class Adapter(private var skillList: ArrayList<Skill>, val fragment: Fragment):RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var onClickListener: OnClickListener?=null

    private fun filterList(filterList: ArrayList<Skill>){
        skillList = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SkillListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return skillList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentSkill = skillList[position]
        holder.tvName.text=currentSkill.name
        holder.tvImage.setImageDrawable(ResourcesCompat.getDrawable(holder.tvImage.resources, currentSkill.image, null))

        holder.itemView.setOnClickListener {
            if (onClickListener!=null){
                onClickListener!!.onClick(position, currentSkill)
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, model: Skill)
    }

    fun filter(text: String){
        val filteredList = ArrayList<Skill>()
        val emptyList = ArrayList<Skill>()

        for (item in Constants.getSkillData(fragment)){
            if (item.name.contains(text, ignoreCase = true)){
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()){
            filterList(filteredList)
        }else{
            filterList(emptyList)
        }
    }

    inner class MyViewHolder(binding: SkillListBinding):RecyclerView.ViewHolder(binding.root){
        val tvName = binding.tvName
        val tvImage = binding.tvImage
    }
}