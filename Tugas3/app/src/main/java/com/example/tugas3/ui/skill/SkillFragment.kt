package com.example.tugas3.ui.skill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas3.Constants
import com.example.tugas3.Skill
import com.example.tugas3.databinding.FragmentSkillBinding
import com.example.tugas3.Adapter

class SkillFragment : Fragment() {

    private var _binding: FragmentSkillBinding?=null
    private val binding get() = _binding!!

    private var itemAdapter:Adapter = Adapter(Constants.getSkillData())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val searchView = binding.search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        recyclerView.adapter = itemAdapter

        itemAdapter.setOnClickListener(object :
            Adapter.OnClickListener{
            override fun onClick(position: Int, model: Skill) {
                findNavController().navigate(SkillFragmentDirections.actionNavSkillToNavInfo(model.name))
            }
        })
        return root
    }

    fun filter(text: String){
        val filteredList = ArrayList<Skill>()
        val emptyList = ArrayList<Skill>()

        for (item in Constants.getSkillData()){
            if (item.name.contains(text, ignoreCase = true)){
                filteredList.add(item)
            }
        }
        if (filteredList.isNotEmpty()){
            itemAdapter.filterList(filteredList)
        }else{
            itemAdapter.filterList(emptyList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}