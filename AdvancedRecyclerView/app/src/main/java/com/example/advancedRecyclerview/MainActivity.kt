package com.example.advancedRecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity :
        AppCompatActivity(),
        HomeRvClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questions = listQueAns(300)
        val trafficSigns = listTrafficIcons(100)

        findViewById<RecyclerView>(R.id.rv).apply {
            adapter = HomeListAdapter(
                    questions,
                    trafficSigns,
                    this@MainActivity
            )
            layoutManager = LinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL,
                    false
            )
        }
    }

    override fun onClickRvVertical(data: Question, QNo: Int) {
    }

    override fun onClickRvHorizontal(position: Int) {
    }
}