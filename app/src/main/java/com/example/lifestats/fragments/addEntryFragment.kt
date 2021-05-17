package com.example.lifestats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.lifestats.R
import com.example.lifestats.data.Entry
import com.example.lifestats.data.EntryValue
import com.example.lifestats.data.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add_entry.*
import kotlinx.android.synthetic.main.fragment_add_entry.view.*

class addEntryFragment : Fragment() {

    private lateinit var entryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_entry, container, false)

        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        view.add_button.setOnClickListener(){
            insertEntry()
        }

        return view
    }

    private fun insertEntry() {
        val value = value_box.text.toString().toLong()
        val val_descrip = description_box.text.toString()

        val entry = Entry(0,val_descrip)
        val entryValue = EntryValue(0,entry.entryId,value)
        //entryViewModel.addEntry(entry)
        //entryViewModel.addEntryValue(entryValue)
        findNavController().navigate(R.id.action_addEntryFragment_to_entryFragment)

        Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()

    }
}
