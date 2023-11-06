package com.example.save_my_memories

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import java.util.*

class MemoryAdapter(var context: Context) : RecyclerView.Adapter<MemoryAdapter.MemoryViewHolder?>() {
//    var bookingModelList: MutableList<String>

//    init {
//        this.bookingModelList = bookingModelList
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.row_memory, parent, false)
        return MemoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: MemoryViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {

    }


    inner class MemoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }



}