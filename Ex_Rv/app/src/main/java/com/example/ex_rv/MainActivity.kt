package com.example.ex_rv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.ex_rv.adapter.RecyclerViewAdapter
import com.example.ex_rv.data_generator.generateContact

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contact = generateContact(50)

        val rv = findViewById<RecyclerView>(R.id.rv)
        rv.adapter = RecyclerViewAdapter(contact)
    }
}