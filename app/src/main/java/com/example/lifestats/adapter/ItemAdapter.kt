package com.example.lifestats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestats.R
import com.example.lifestats.data.EntryWithValues
import com.example.lifestats.fragments.EntryFragmentDirections
import kotlinx.android.synthetic.main.data_list_view.view.*

class ItemAdapter ():
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() { // Refer to book for onClickListener help

    private var listItems = emptyList<EntryWithValues>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        internal var entryDate = view.findViewById<View>(R.id.entry_date) as TextView
        internal var entryVal = view.findViewById<View>(R.id.entry_value) as TextView
        internal var entryDescrip = view.findViewById<View>(R.id.entry_description) as TextView
        internal var units = view.findViewById<View>(R.id.units) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.data_list_view,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentEntryAll = listItems[position]
        val currentEntry = currentEntryAll.entry
        val currentEntryValueList = currentEntryAll.entries

//        if (currentEntryAll.entries.isNotEmpty()){
//            currentEntryValue = currentEntryAll.entries[0]
//        }
        var hour_string = currentEntry.hour.toString()
        var minute_string = currentEntry.minutes.toString()
        if (currentEntry.hour < 10){
            hour_string = "0" + currentEntry.hour
        }

        if (currentEntry.minutes < 10) {
            minute_string = "0" + currentEntry.minutes.toString()
        }
        val date = (currentEntry.month + 1).toString() + "/" + currentEntry.day.toString() + ", " + hour_string + ":" + minute_string
        holder.entryDate.text =  date
        for (item in currentEntryAll.entries){
            holder.entryVal.text = item.entry_value.toString()
            holder.units.text = item.value_unit.toString()
        }

        holder.entryDescrip.text = currentEntry.descrip

        holder.itemView.entry_row_layout.setOnClickListener(){
            val action = EntryFragmentDirections.actionEntryFragmentToUpdateEntryFragment(currentEntry,currentEntryValueList)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = listItems.size

    fun setData(entryWithValues: List<EntryWithValues>) {
        this.listItems = entryWithValues
        notifyDataSetChanged()
    }

}