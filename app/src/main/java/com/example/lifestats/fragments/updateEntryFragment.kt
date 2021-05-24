package com.example.lifestats.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.lifestats.R
import com.example.lifestats.data.Entry
import com.example.lifestats.data.EntryViewModel
import kotlinx.android.synthetic.main.fragment_update_entry.*
import kotlinx.android.synthetic.main.fragment_update_entry.view.*
import java.util.*
import java.util.Calendar.*

class updateEntryFragment : Fragment() {

    private val args by navArgs<updateEntryFragmentArgs>()
    private lateinit var entryViewModel: EntryViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_entry, container, false)

        view.update_value_box.setText(args.currentEntry.entry_value.toString())
        view.update_description_box.setText(args.currentEntry.descrip)
        view.date_text.setText(args.currentEntry.date)

        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        view.cal_icon.setOnClickListener(){
            val month = args.currentEntry.date.substring(0,2).toInt() - 1
            val day = args.currentEntry.date.substring(2,4).toInt()
            val year = args.currentEntry.date.substring(4).toInt()
            val update_date = Calendar.getInstance()
            update_date.set(MONTH, month, DAY_OF_MONTH, day, YEAR, year)
            val dateDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener(){ view, year, month, day ->
            },year, month, day)
            dateDialog.show()
        }

        view.update_button.setOnClickListener(){
            updateEntry()
        }

        view.delete_btn.setOnClickListener(){
            val alertBuilder = AlertDialog.Builder(requireContext())
            alertBuilder.setTitle("Delete Entry")
            alertBuilder.setMessage("Do you want to delete this entry?")

            alertBuilder.setPositiveButton("Delete"){
                    _,_ -> entryViewModel.deleteEntry(args.currentEntry)
                Toast.makeText(requireContext(),"Sucessfully deleted",Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)
            }
            alertBuilder.setNegativeButton("Cancel"){_,_ -> }

            alertBuilder.create().show()
        }


        return view
    }

    private fun updateEntry() {
        val update_value = update_value_box.text.toString().toLong()
        val update_descrip = update_description_box.text.toString()
        val update_date = date_text.text.toString() // pretty sure this needs to change

        val updatedEntry = Entry(args.currentEntry.entryId, update_value, update_date, update_descrip)
        entryViewModel.updateEntry(updatedEntry)
        findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

        Toast.makeText(requireContext(),"Successfully updated!",Toast.LENGTH_SHORT).show()

    }
}
