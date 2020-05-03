package dev.falcer.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.google.android.material.appbar.AppBarLayout
import dev.falcer.movieapp.R
import dev.falcer.movieapp.utils.FakeDataConverter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.rv_category
import kotlinx.android.synthetic.main.activity_main.rv_recent
import kotlinx.android.synthetic.main.activity_main.vp_trending
import kotlinx.android.synthetic.main.activity_main_custom.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private val trendingAdapter = HomeTrendingAdapter()
    private val categoryAdapter = HomeCategoryAdapter()
    private val recentAdapter = HomeRecentAdapter()
    private val mainAdapter = MainAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        setContentView(R.layout.activity_main_custom)
        setSupportActionBar(toolbar)
        rv_main.apply {
            adapter = mainAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2).apply {
                spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
                    override fun getSpanSize(position: Int): Int {
                        return if(position <= 1){
                            2
                        } else {
                           1
                        }
                    }
                }
            }
        }

        setupTrending()
        setupCategory()
        setupRecent()
    }

    private fun setupTrending() {
        trendingAdapter.setData(FakeDataConverter.getMovieFromAsset(this.baseContext, "trending.json")!!)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(56))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.95f + r * 0.15f
        }
        vp_trending.apply {
            adapter = trendingAdapter
            clipChildren = false
            clipToPadding = false
            offscreenPageLimit = 3
        }
        vp_trending.setPageTransformer(compositePageTransformer)
    }

    private fun setupCategory() {
        mainAdapter.addCategory(FakeDataConverter.getGenreFromAsset(this.baseContext, "genres.json")!!.slice(0..3))
    }

    private fun setupRecent() {
        mainAdapter.addRecent(FakeDataConverter.getMovieFromAsset(this.baseContext, "now_playing.json")!!.slice(0..3))

    }
}
