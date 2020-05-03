package dev.falcer.movieapp.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.falcer.movieapp.data.entity.Genre
import dev.falcer.movieapp.data.entity.Movie

class MainAdapter(private val listRecent: MutableList<Movie> = mutableListOf(), private val listCategory: MutableList<Genre> = mutableListOf()) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_CATEGORY_GROUP -> CategoryGroupViewHolder.create(parent)
            TYPE_RECENT_GROUP -> RecentHeaderViewHolder.create(parent)
            else -> RecentItemViewHolder.create(parent)
        }
    }

    fun addRecent(list: List<Movie>?){

        listRecent.clear()
        list?.let{
            listRecent.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun addCategory(list: List<Genre>?){

        listCategory.clear()
        list?.let{
            listCategory.addAll(it)
        }
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int {
        return 2 + listRecent.size
    }

    override fun getItemViewType(position: Int): Int {
        return when{
            position == 0 -> TYPE_CATEGORY_GROUP
            position == 1 -> TYPE_RECENT_GROUP
            position > 1 -> TYPE_RECENT_ITEM
            else -> super.getItemViewType(position)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CategoryGroupViewHolder -> {
                holder.bind(listCategory)
            }
            is RecentHeaderViewHolder -> {
                holder.bind()
            }
            is RecentItemViewHolder -> {
                holder.bind(listRecent[position-2])
            }
        }
    }


    companion object{
        const val TYPE_CATEGORY_GROUP = 0
        const val TYPE_RECENT_GROUP = 1
        const val TYPE_RECENT_ITEM = 2
    }
}