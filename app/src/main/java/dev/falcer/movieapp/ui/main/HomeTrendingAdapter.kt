package dev.falcer.movieapp.ui.main

import android.annotation.SuppressLint
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.shapeofview.shapes.RoundRectView
import dev.falcer.movieapp.BuildConfig
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Movie

class HomeTrendingAdapter : RecyclerView.Adapter<HomeTrendingAdapter.ViewHolder>() {

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
            LayoutInflater.from(parent.context).inflate(R.layout.card_trending, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val root = view.findViewById<RoundRectView>(R.id.root)
        private val poster = view.findViewById<ImageView>(R.id.iv_poster)
        private val title = view.findViewById<TextView>(R.id.tv_title)
        private val duration = view.findViewById<TextView>(R.id.tv_duration)

        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            Glide.with(itemView.context).load("${BuildConfig.IMAGE_URL}${movie.backdropPath}")
                .centerCrop().into(poster)
            val titleText = "${movie.title} (${movie.releaseDate.slice(0..3)})"
            val spannableTitle = SpannableString(titleText)
            spannableTitle.setSpan(
                RelativeSizeSpan(.65f),
                titleText.length - 7,
                titleText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableTitle.setSpan(
                ForegroundColorSpan(Color.parseColor("#CCFFFFFF")),
                titleText.length - 7,
                titleText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            title.text = spannableTitle
        }
    }

}