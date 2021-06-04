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

        // Assign the view model to entryViewModel to allow UI to interface with the database
        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        // Set click listener for the add button, allowing users to enter their new entry
        view.add_button.setOnClickListener(){
            // Check if the value box is blank. If it is, prompt the user to enter a value instead of trying to submit the entry
            if (value_box.text.isBlank()){
                Toast.makeText(requireContext(),"Please enter a value",Toast.LENGTH_SHORT).show()
            }
            else {
                insertEntry()
            }
        }

        return view
    }

    // insertEntry adds the entry to the database
    private fun insertEntry() {

        // Collect the user entries from the text boxes in the layout
        val value = value_box.text.toString().toLong()
        val value_unit = unit_spinner.selectedItem.toString()
        val val_descrip = description_box.text.toString()

        // Get an instance of a calendar and record the current date and time as the initial date and time for the entry
        val currentDate = Calendar.getInstance()
        val hour = currentDate.get(Calendar.HOUR_OF_DAY)
        val minutes = currentDate.get(Calendar.MINUTE)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val month = currentDate.get(Calendar.MONTH)
        val year = currentDate.get(Calendar.YEAR)

        // Create the Entry object and submit it to the database
        val entry = Entry(0,value, value_unit, minutes, hour, day, month, year,val_descrip)
        entryViewModel.addEntry(entry)
        findNavController().navigate(R.id.action_addEntryFragment_to_entryFragment)

        Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()

    }
}
