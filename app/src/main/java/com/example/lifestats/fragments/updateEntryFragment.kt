package com.example.lifestats.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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

class updateEntryFragment : Fragment() {

    private val args by navArgs<updateEntryFragmentArgs>()
    private lateinit var entryViewModel: EntryViewModel
    private var new_minute: Int = -1
    private var new_hour: Int = -1
    private var new_month: Int = -1
    private var new_day: Int = -1
    private var new_year: Int = -1

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_entry, container, false)

        view.update_value_box.setText(args.currentEntry.entry_value.toString())
        view.update_description_box.setText(args.currentEntry.descrip)
        view.value_units.setText(args.currentEntry.value_unit)

        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        view.cal_icon.setOnClickListener() {
            val dateDialog = DatePickerDialog(
                requireContext(), DatePickerDialog.OnDateSetListener
                { view, year, month, day ->
                    new_year = year
                    new_month = month
                    new_day = day
                }, args.currentEntry.year, args.currentEntry.month, args.currentEntry.day
            )
            dateDialog.show()
        }

            view.clock_icon.setOnClickListener {
                val timeDialog = TimePickerDialog(
                    requireContext(),
                    TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                        new_hour = hour
                        new_minute = minute
                    },
                    args.currentEntry.hour,
                    args.currentEntry.minutes,
                    true
                )
                timeDialog.show()
            }

        view.update_button.setOnClickListener() {
            if (view.update_value_box.text.isEmpty()){
                Toast.makeText(requireContext(), "Please enter a value",Toast.LENGTH_SHORT).show()
            }
            else {
                updateEntry()
            }
        }

        view.delete_btn.setOnClickListener() {
            val alertBuilder = AlertDialog.Builder(requireContext())
            alertBuilder.setTitle("Delete Entry")
            alertBuilder.setMessage("Do you want to delete this entry?")

            alertBuilder.setPositiveButton("Delete") { _, _ ->
                entryViewModel.deleteEntry(args.currentEntry)
                Toast.makeText(requireContext(), "Sucessfully deleted", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)
            }
            alertBuilder.setNegativeButton("Cancel") { _, _ -> }

            alertBuilder.create().show()
        }


        return view
    }

    private fun updateEntry() {
        val update_value = update_value_box.text.toString().toLong()
        val update_descrip = update_description_box.text.toString()
        if (new_day == -1 && new_hour == -1) { // Day and time were not changed
            val updatedEntry = Entry(
                args.currentEntry.entryId,
                update_value,
                args.currentEntry.value_unit,
                args.currentEntry.minutes,
                args.currentEntry.hour,
                args.currentEntry.day,
                args.currentEntry.month,
                args.currentEntry.year,
                update_descrip
            )
            entryViewModel.updateEntry(updatedEntry)
            findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()

        } else if (new_hour == -1) { //Only day was changed
            val updatedEntry = Entry(
                args.currentEntry.entryId,
                update_value,
                args.currentEntry.value_unit,
                args.currentEntry.minutes,
                args.currentEntry.hour,
                new_day,
                new_month,
                new_year,
                update_descrip
            )
            entryViewModel.updateEntry(updatedEntry)
            findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        } else if (new_day == -1) { // Only hour was changed
            val updatedEntry = Entry(
                args.currentEntry.entryId,
                update_value,
                args.currentEntry.value_unit,
                new_minute,
                new_hour,
                args.currentEntry.day,
                args.currentEntry.month,
                args.currentEntry.year,
                update_descrip
            )
            entryViewModel.updateEntry(updatedEntry)
            findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        } else { // neither day nor hour was changed
            val updatedEntry = Entry(
                args.currentEntry.entryId,
                update_value,
                args.currentEntry.value_unit,
                new_minute,
                new_hour,
                new_day,
                new_month,
                new_year,
                update_descrip
            )
            entryViewModel.updateEntry(updatedEntry)
            findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        }
    }
}