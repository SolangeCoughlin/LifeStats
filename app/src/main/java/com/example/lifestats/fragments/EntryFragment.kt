package com.example.lifestats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifestats.R
import com.example.lifestats.adapter.ItemAdapter
import com.example.lifestats.data.EntryViewModel
import kotlinx.android.synthetic.main.fragment_entry.view.*


class EntryFragment() : Fragment() {

    private lateinit var entryViewModel: EntryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        //create adapter and setup recyclerView
        val adapter = ItemAdapter()

        val recyclerView = view.data_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize user view model
        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        //use view model to get all entries and set an observer on it
        entryViewModel.getAllEntries.observe(viewLifecycleOwner,
            Observer { entry -> adapter.setData(entry)})

        view.new_entry.setOnClickListener(){
            findNavController().navigate(R.id.action_entryFragment_to_addEntryFragment)
        }

        return view

    }


}