package dev.falcer.movieapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.falcer.movieapp.BuildConfig
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Movie
import kotlinx.android.synthetic.main.card_movie_custom.view.*

class RecentItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){

    fun bind(movie: Movie){
        with(view){
            Glide.with(context).load("${BuildConfig.IMAGE_URL}${movie.posterPath}")
                .centerCrop().into(view.iv_poster)
            view.tv_title.text = movie.title
        }

    }

    companion object {
        fun create(parent: ViewGroup): RecentItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_movie_custom, parent, false)
            return RecentItemViewHolder(view)
        }
    }
}