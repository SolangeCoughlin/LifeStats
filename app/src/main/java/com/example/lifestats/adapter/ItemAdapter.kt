package com.example.lifestats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestats.R
import com.example.lifestats.data.Entry
import com.example.lifestats.fragments.EntryFragmentDirections
import kotlinx.android.synthetic.main.data_list_view.view.*

class   ItemAdapter ():
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Initialize an empty list of Entries from the database that the ItemAdapter will display
    private var listItems = emptyList<Entry>()

    // the subclass ItemViewHolder defines the content of each individual list item in the RecyclerView
    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        internal var entryDate = view.findViewById<View>(R.id.entry_date) as TextView
        internal var entryVal = view.findViewById<View>(R.id.entry_value) as TextView
        internal var entryDescrip = view.findViewById<View>(R.id.entry_description) as TextView
        internal var units = view.findViewById<View>(R.id.units) as TextView

    }

    // This function creates an ItemViewHolder object, setting its layout to the layout defined in data_list_view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.data_list_view,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    // This function populates the ItemViewHolder with data from a specific Entry stored in listItems.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentEntry = listItems[position]
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
        holder.entryVal.text = currentEntry.entry_value.toString()
        holder.entryDescrip.text = currentEntry.descrip
        holder.units.text = currentEntry.value_unit

        // A click listener is defined here to navigate the user to the update screen for a specific Entry when they click on it
        holder.itemView.entry_row_layout.setOnClickListener(){
            val action = EntryFragmentDirections.actionEntryFragmentToUpdateEntryFragment(currentEntry)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = listItems.size

    // This function is used to notify observers when the data being displayed changes
    fun setData(entry: List<Entry>) {
        this.listItems = entry
        notifyDataSetChanged()
    }


}