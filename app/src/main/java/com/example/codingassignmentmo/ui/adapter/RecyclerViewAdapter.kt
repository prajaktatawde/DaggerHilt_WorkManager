package com.example.codingassignmentmo.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.codingassignmentmo.databinding.RowRepoListBinding
import com.example.codingassignmentmo.model.ReposData
import com.example.codingassignmentmo.ui.fragments.RepoListFragmentDirections

class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var items : List<ReposData>? = null

    fun setRepoListData(items: List<ReposData>){
        this.items= items
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RowRepoListBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        items?.let { data ->
            holder.bind(data[position])
            holder.binding.root.setOnClickListener {
                val action = RepoListFragmentDirections.actionRepoListFragmentToRepoDetailFragment(
                    data[position]
                )
                holder.binding.root.findNavController().navigate(action)
            }
        }

    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    class MyViewHolder(val binding: RowRepoListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: ReposData){
            binding.trendRepoData = data
            binding.executePendingBindings()
        }
    }
}