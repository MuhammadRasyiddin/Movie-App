package dev.falcer.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Movie
import kotlinx.android.synthetic.main.item_header_recent.view.*

class RecentHeaderViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    fun bind(){


    }

    companion object {
        fun create(parent: ViewGroup): RecentHeaderViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header_recent, parent, false)
            return RecentHeaderViewHolder(view)
        }
    }
}