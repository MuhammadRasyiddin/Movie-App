package dev.falcer.movieapp.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.falcer.movieapp.R
import dev.falcer.movieapp.data.entity.Genre

class HomeCategoryAdapter : RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder>() {

    private lateinit var dataList: List<Genre>

    fun setData(data: List<Genre>) {
        dataList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)
        )
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val root = view.findViewById<LinearLayout>(R.id.root)
        private val icon = view.findViewById<ImageView>(R.id.iv_icon)
        private val name = view.findViewById<TextView>(R.id.tv_name)

        @SuppressLint("SetTextI18n")
        fun bind(genre: Genre) {
            name.text = genre.name
        }
    }

}