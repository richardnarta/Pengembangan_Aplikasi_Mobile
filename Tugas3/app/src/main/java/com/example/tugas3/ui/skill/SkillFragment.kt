package com.example.tugas3.ui.skill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas3.ui.utilities.Constants
import com.example.tugas3.databinding.FragmentSkillBinding

class SkillFragment : Fragment() {
    private var _binding: FragmentSkillBinding?=null
    private val binding get() = _binding!!

    private lateinit var itemAdapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSkillBinding.inflate(inflater, container, false)
        val root: View = binding.root

        itemAdapter = Adapter(Constants.getSkillData(this), this)

        createRecycleView()

        defineSearchView()

        defineOnClickListener()

        return root
    }

    private fun createRecycleView(){
        val recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter
    }

    private fun defineSearchView(){
        val searchView = binding.search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                itemAdapter.filter(newText)
                return false
            }
        })
    }

    private fun defineOnClickListener(){
        itemAdapter.setOnClickListener(object :
            Adapter.OnClickListener{
            override fun onClick(position: Int, model: Skill) {
                findNavController().navigate(SkillFragmentDirections.actionNavSkillToNavInfo(model.name))
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}