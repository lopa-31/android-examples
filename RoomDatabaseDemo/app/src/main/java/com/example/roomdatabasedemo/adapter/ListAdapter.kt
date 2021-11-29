package com.example.roomdatabasedemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabasedemo.R
import com.example.roomdatabasedemo.data.model.User
import com.example.roomdatabasedemo.fragment.ListFragmentDirections


class ListAdapter : RecyclerView.Adapter<ListAdapter.VerticalViewHolder>() {

    private var userList = emptyList<User>()

    inner class VerticalViewHolder(private val view: View) :
            RecyclerView.ViewHolder(view) {

        fun bindData(data: User) {
            val ageTv = view.findViewById<TextView>(R.id.age_tv)
            val fnTv = view.findViewById<TextView>(R.id.fn_tv)
            val lnTv = view.findViewById<TextView>(R.id.ln_tv)

            data.apply {
                ageTv.text = age.toString()
                fnTv.text = firstName.toString()
                lnTv.text = lastName.toString()
            }

            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(data)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

        return VerticalViewHolder(v)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bindData(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        userList = user
        notifyDataSetChanged()
    }
}