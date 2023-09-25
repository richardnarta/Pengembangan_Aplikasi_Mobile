package com.example.tugas2.ui.skill

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas2.Adapter
import com.example.tugas2.R
import com.example.tugas2.Constants
import com.example.tugas2.Skill
import com.example.tugas2.databinding.FragmentSkillBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SkillFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSkillBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val skillLists= Constants.getSkillData()
        val itemAdapter=Adapter(skillLists)
        val recyclerView:RecyclerView=view.findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = itemAdapter

        itemAdapter.setOnClickListener(object :
            Adapter.OnClickListener{
            override fun onClick(position: Int, model : Skill){
                val bundle = bundleOf(InfoFragment.EXTRA_NAME to model.name)
                findNavController().navigate(R.id.action_nav_skill_to_info_frag, bundle)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}