package com.example.lifestats.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_entry, container, false)

        view.update_value_box.setText(args.currentEntry.entry_value.toString())
        view.update_description_box.setText(args.currentEntry.descrip)

        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)

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

        val updatedEntry = Entry(args.currentEntry.entryId, update_value, update_descrip)
        entryViewModel.updateEntry(updatedEntry)
        findNavController().navigate(R.id.action_updateEntryFragment_to_entryFragment)

        Toast.makeText(requireContext(),"Successfully updated!",Toast.LENGTH_SHORT).show()

    }
}
