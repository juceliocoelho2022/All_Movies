package com.devjucelio.allmovies.ui
import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.devjucelio.allmovies.AppConstants
import com.devjucelio.allmovies.R
import com.devjucelio.allmovies.dto.MovieDTO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class GridAdapter(
    private val context: Context?,
    private val onMovieClick: OnMovieClick,
    private var list: List<MovieDTO>
) : RecyclerView.Adapter<RecommendedHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendedHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list_item_recommended, parent, false)
        return RecommendedHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecommendedHolder, position: Int) {
        Log.d("grid-log", "onBindViewHolder() called, position = $position, list.size = ${list.size}")
        // get device dimensions
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        val maxHeight= width.div(3) * 1.4

        holder.poster.minimumHeight = maxHeight.toInt()
        holder.cardView.layoutParams.height = maxHeight.toInt()

        holder.apply {
           Picasso.get()
                .load("${AppConstants.TMDB_IMAGE_BASE_URL_W185}${list[position].poster_path}")
                .placeholder(R.drawable.placeholder)
                .into(poster)

            cardView.setOnClickListener {
                val id = list[position].id
                if (id != null) {
                    Log.d("clickgrid", "GridAdapter, setOnClickListener, id = $id")
                    onMovieClick.onClick(id)
                }
                else {
                    Toast.makeText(
                        context,
                        "Sorry. Can not load this movie. :/",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    fun setList(list: List<MovieDTO>) {
        Log.d("grid-log", "setList() called")
        this.list = list
        notifyDataSetChanged()
    }
}

class RecommendedHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var poster = itemView.iv_movie_poster
    var cardView = itemView.cv_movie
}

