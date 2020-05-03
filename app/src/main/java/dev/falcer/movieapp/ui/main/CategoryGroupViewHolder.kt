package dev.falcer.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Genre
import kotlinx.android.synthetic.main.activity_main.view.*

class CategoryGroupViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private var categoryAdapter = HomeCategoryAdapter()
    fun bind(listGenre: List<Genre>){
        with(view){
            categoryAdapter.setData(listGenre)
            rv_category.apply {
                adapter = categoryAdapter
                layoutManager = LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
            }
        }

    }


    companion object {
        fun create(parent: ViewGroup): CategoryGroupViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_group_category, parent, false)
            return CategoryGroupViewHolder(view)
        }
    }
}