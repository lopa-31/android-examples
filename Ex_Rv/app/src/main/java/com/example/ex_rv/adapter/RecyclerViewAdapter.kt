package com.example.ex_rv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ex_rv.R
import com.example.ex_rv.model.Contact

class RecyclerViewAdapter(val list : List<Contact>) :
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>(){

    inner class RecyclerViewHolder(val ItemView: View): RecyclerView.ViewHolder(ItemView){

        fun bindContact(contact: Contact){
            val name = ItemView.findViewById<TextView>(R.id.contact_name)
            val number = ItemView.findViewById<TextView>(R.id.contact_number)

            name.text = contact.name
            number.text = contact.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.RecyclerViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return RecyclerViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.RecyclerViewHolder, position: Int){
        holder.bindContact(contact = list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}