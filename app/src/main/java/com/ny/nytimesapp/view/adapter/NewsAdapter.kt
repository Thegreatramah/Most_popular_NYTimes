package com.ny.nytimesapp.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ny.nytimesapp.R
import com.ny.nytimesapp.databinding.ItemCellBinding
import com.ny.nytimesapp.model.Result
import com.ny.nytimesapp.view.NewsDetailsActivity
import com.ny.nytimesapp.viewModel.NewsViewModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var resultList = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemCellBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_cell, parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    fun updateResultList(results: List<Result>) {
        resultList.addAll(results)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemCellBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = NewsViewModel()

        fun bind(result: Result) {
            viewModel.bind(result)
            binding.viewModel = viewModel

            binding.cardView.setOnClickListener {
                val intent = Intent(context, NewsDetailsActivity::class.java)
                intent.putExtra("URL", result.url)
                context.startActivity(intent)
            }
        }
    }

}