package com.example.lifestats.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.lifestats.R
import com.example.lifestats.data.EntryViewModel
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import java.util.*

class dashboardFragment : Fragment() {

    private lateinit var entryViewModel: EntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Create calendar object and use it to determine today's date
        val currentDate = Calendar.getInstance()
        val day = currentDate.get(Calendar.DAY_OF_MONTH)
        val month = currentDate.get(Calendar.MONTH)
        val year = currentDate.get(Calendar.YEAR)

        view.metric_button.setOnClickListener(){
            findNavController().navigate(R.id.action_dashboard_to_entryFragment)
        }


        // Initialize user view model
        entryViewModel = ViewModelProviders.of(this).get(EntryViewModel::class.java)


        val entries_today = entryViewModel.getDates(day, month, year)
//        if (entries_today.isEmpty()){
//            view.checkBox.setChecked()
//        }
        Toast.makeText(requireContext(),entries_today.toString(),Toast.LENGTH_SHORT).show()

        return view
    }

}
