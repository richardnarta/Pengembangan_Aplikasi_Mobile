package com.example.tugas2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas2.databinding.SkillListBinding

class Adapter(private val skillLists: ArrayList<Skill>) : RecyclerView.Adapter<Adapter.MyViewHolder>(){
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            SkillListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return skillLists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentSkill = skillLists[position]
        holder.tvName.text=currentSkill.name

        val drawables = listOf(
            R.drawable.python,
            R.drawable.cplusplus,
            R.drawable.js,
            R.drawable.kotlin,
        )

        holder.tvImage.setImageDrawable(
            ResourcesCompat.getDrawable(holder.tvImage.resources, drawables[position], null)
        )

        holder.itemView.setOnClickListener {
            if (onClickListener != null){
                onClickListener!!.onClick(position,currentSkill)
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position: Int, model: Skill)
    }

    class MyViewHolder(binding: SkillListBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName = binding.tvName
        val tvImage = binding.tvImage
    }
}