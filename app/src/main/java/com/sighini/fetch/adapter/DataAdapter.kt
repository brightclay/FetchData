package com.sighini.fetch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sighini.fetch.ModelData
import com.sighini.fetch.R
import com.sighini.fetch.databinding.ListRowItemBinding


class DataAdapter(val context: Context, var list: ArrayList<ModelData>): RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val listRowItemBinding: ListRowItemBinding =  DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_row_item, parent, false)
        return DataViewHolder(listRowItemBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
       holder.binding.data = list.get(position)
    }
}

class DataViewHolder(val binding: ListRowItemBinding): RecyclerView.ViewHolder(binding.root) {}
