package com.example.advancedRecyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeListAdapter(
        private val listOfQuestions: List<Question>,
        private val listOfTrafficSigns: List<TrafficSigns>,
        private val homeRvClickListener: HomeRvClickListener,
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_TRAFFIC_ICON = 0
        private const val TYPE_QUESTIONS = 1
    }

    inner class TrafficViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        init {
            view.findViewById<RecyclerView>(R.id.rv_horizontal).apply {
                adapter = HomeTrafficSignAdapter(listOfTrafficSigns, homeRvClickListener)
                layoutManager = GridLayoutManager(
                        context,
                        2,
                        LinearLayoutManager.HORIZONTAL,
                        false
                )
            }
        }
    }

    inner class QuestionViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        fun bindData(data: Question, QNo: Int) {
            val que = view.findViewById<TextView>(R.id.que)
            val ans = view.findViewById<TextView>(R.id.ans)

            que.text = "$QNo. ${data.que}"
            ans.text = "Ans: ${data.ans}"

            view.setOnClickListener {
                homeRvClickListener.onClickRvVertical(data, QNo)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_TRAFFIC_ICON -> {
                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_home_traffic_sign_card, parent, false)
                TrafficViewHolder(v)
            }
            else -> {
                val v = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_home_questions, parent, false)

                QuestionViewHolder(v)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is QuestionViewHolder) {
            holder.bindData(listOfQuestions[position - 1], position)
        }
    }

    override fun getItemCount() = listOfQuestions.size + 1

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_TRAFFIC_ICON
            else -> TYPE_QUESTIONS
        }
    }
}

class HomeTrafficSignAdapter(
        private val list: List<TrafficSigns>,
        val homeRvClickListener: HomeRvClickListener)
    : RecyclerView.Adapter<HomeTrafficSignAdapter.MyView>() {

    inner class MyView(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindImages(sign: TrafficSigns) {
            val imageView1 = view.findViewById<ImageView>(R.id.imageView1)
            val textView1 = view.findViewById<TextView>(R.id.textView1)

            imageView1.setImageResource(sign.signId)
            textView1.text = sign.signName

            view.setOnClickListener {
                homeRvClickListener.onClickRvHorizontal(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val itemView: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_home_traffic_signs, parent, false)

        return MyView(itemView)
    }

    override fun onBindViewHolder(
            holder: MyView,
            position: Int
    ) {
        holder.bindImages(list[position])
    }

    override fun getItemCount(): Int = list.size
}

interface HomeRvClickListener {
    fun onClickRvVertical(data: Question, QNo: Int)
    fun onClickRvHorizontal(position: Int)
}