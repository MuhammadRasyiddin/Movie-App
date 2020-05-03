package dev.falcer.movieapp.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.falcer.movieapp.BuildConfig
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Movie

class HomeRecentAdapter : RecyclerView.Adapter<HomeRecentAdapter.ViewHolder>() {

    private lateinit var dataList: List<Movie>

    fun setData(data: List<Movie>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_movie, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val root = view.findViewById<LinearLayout>(R.id.root)
        private val poster = view.findViewById<ImageView>(R.id.iv_poster)
        private val title = view.findViewById<TextView>(R.id.tv_title)

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            Glide.with(itemView.context).load("${BuildConfig.IMAGE_URL}${movie.backdropPath}")
                .centerCrop().into(poster)
            title.text = movie.title
        }
    }

}