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
import kotlinx.android.synthetic.main.fragment_add_entry.*
import kotlinx.android.synthetic.main.fragment_update_entry.*
import kotlinx.android.synthetic.main.fragment_update_entry.view.*

class updateEntryFragment : Fragment() {

    // Initializing some variables to be used in this fragment. args stores the data for the Entry object being updated, entryViewModel contains the View Model the UI will use
    // to interact with the database, and new_minute through new_year are values used to indicate whether these values are updated by the user.
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

        // Set the text boxes in the UI to the values currently held by the Entry object being updated
        view.update_value_box.setText(args.currentEntry.entry_value.toString())
        view.update_description_box.setText(args.currentEntry.descrip)
        view.value_units.setText(args.currentEntry.value_unit)

        // Get the view model and assign it to the variable initialized for it
        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

        // Set a click listener for the calendar icon, which the user will click to update the date of their entry
        view.cal_icon.setOnClickListener() {
            openDatePicker()
        }

        view.change_date.setOnClickListener{
            openDatePicker()
        }

        // Set a click listener for the clock icon, which the user will click to update the time of their entry
        view.clock_icon.setOnClickListener {
            openTimePicker()
        }

        view.change_time.setOnClickListener{
            openTimePicker()
        }


        // Set a click listener for the update button
        view.update_button.setOnClickListener() {

            // CHeck if the value field is blank. If it is, prompt the user to enter a value instead of calling updateEntry

            if (view.update_value_box.text.isEmpty()){
                Toast.makeText(requireContext(), "Please enter a value",Toast.LENGTH_SHORT).show()
            }
            else {
                updateEntry()
            }
        }

        // Set a click listener for the deleteButton
        view.delete_btn.setOnClickListener() {

            // Prompt the user to confirm they would like to delete before deleting the value
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

    private fun openDatePicker(){
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

    private fun openTimePicker(){
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

    // updateEntry submits any updated values to the database
    private fun updateEntry() {

        // Get the values entered by the user
        val update_value = update_value_box.text.toString().toLong()
        val update_descrip = update_description_box.text.toString()

        // If new_day and new_hour are both still -1, both the day and time were not updated. Use the values stored in args to update the database
        if (new_day == -1 && new_hour == -1) {
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


            // If new_day changed but new_hour has not, only the date was updated.
        } else if (new_hour == -1) {
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
            //findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

        // If new_day has not changed but new_hour has, only the time was changed
        } else if (new_day == -1) {
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
            //findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

        // Otherwise, day and hour were both changed
        } else {
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
            //findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)


        }
        Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)
    }
}