package com.example.save_my_memories

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainDashboard : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var memoryAdapter: MemoryAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_dashboard)

        recyclerView=findViewById(R.id.rv_memory)
        recyclerView.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        memoryAdapter = MemoryAdapter(this)
        recyclerView.adapter = memoryAdapter
    }
}