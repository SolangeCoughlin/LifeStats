package com.example.lifestats.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifestats.R
import com.example.lifestats.data.EntryWithEntryValue
import kotlinx.android.synthetic.main.data_list_view.view.*

class ItemAdapter ():
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() { // Refer to book for onClickListener help

    private var listItems = emptyList<EntryWithEntryValue>()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        internal var entryId = view.findViewById<View>(R.id.entry_id_text) as TextView
        internal var entryVal = view.findViewById<View>(R.id.entry_value) as TextView
        internal var entryDescrip = view.findViewById<View>(R.id.entry_description) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.data_list_view,parent,false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentEntry = listItems[position]
//        holder.entryId.text =  currentEntry.entryId.toString()
//        holder.entryVal.text = currentEntry.entry_value.toString()
//        holder.entryDescrip.text = currentEntry.descrip

        holder.itemView.entry_row_layout.setOnClickListener(){
//            val action = EntryFragmentDirections.actionEntryFragmentToUpdateEntryFragment(currentEntry)
//            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = listItems.size

    fun setData(entryWithEntryValue: List<EntryWithEntryValue>) {
        this.listItems = entryWithEntryValue
        notifyDataSetChanged()
    }


}