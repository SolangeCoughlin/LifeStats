package com.example.lifestats.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.lifestats.R
import com.example.lifestats.data.Entry
import com.example.lifestats.data.EntryViewModel
import kotlinx.android.synthetic.main.fragment_add_entry.*
import kotlinx.android.synthetic.main.fragment_add_entry.view.*
import java.util.*

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
        val value_unit = unit_spinner.selectedItem.toString()
        val val_descrip = description_box.text.toString()
        val currentDate = Calendar.getInstance()
        val hour = currentDate.get(Calendar.HOUR_OF_DAY)
        val minutes = currentDate.get(Calendar.MINUTE)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val month = currentDate.get(Calendar.MONTH)
        val year = currentDate.get(Calendar.YEAR)

        val entry = Entry(0,value, value_unit, minutes, hour, day, month, year,val_descrip)
        entryViewModel.addEntry(entry)
        findNavController().navigate(R.id.action_addEntryFragment_to_entryFragment)


        Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()

    }
}
