package com.example.advancedRecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeQuestionListAdapter(private val list: List<Question>,
                              private val onClick: ClickListener)
    : RecyclerView.Adapter<HomeQuestionListAdapter.VerticalViewHolder>() {

    inner class VerticalViewHolder(val view: View):
        RecyclerView.ViewHolder(view){
            @SuppressLint("SetTextI18n")
            fun bindData(data : Question, QNo : Int){
                val que = view.findViewById<TextView>(R.id.que)
                val ans = view.findViewById<TextView>(R.id.ans)

                que.text = "$QNo. ${data.que}"
                ans.text = "Ans: ${data.ans}"

                view.setOnClickListener{
                    onClick.onClickRvVertical(data, QNo)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_home_questions, parent,false)

        return VerticalViewHolder(v)
    }

    override fun onBindViewHolder(holder: VerticalViewHolder, position: Int) {
        holder.bindData(list[position], position+1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ClickListener{
        fun onClickRvVertical(data : Question, QNo : Int)
    }
}