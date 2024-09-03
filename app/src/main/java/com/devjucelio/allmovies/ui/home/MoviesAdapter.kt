package com.devjucelio.allmovies.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devjucelio.allmovies.AppConstants
import com.devjucelio.allmovies.R
import com.devjucelio.allmovies.dto.MovieDTO
import com.devjucelio.allmovies.ui.OnMovieClick
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MoviesAdapter (val movieClick: OnMovieClick, val context: Context?, val movies: List<MovieDTO>) :
    RecyclerView.Adapter<MoviesAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_list_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        movies.elementAt(position).apply {

            Picasso.get()
                .load("${AppConstants.TMDB_IMAGE_BASE_URL_W185}${poster_path}")
                .placeholder(R.drawable.placeholder)
                .into(holder.poster)

            holder.cardView.setOnClickListener {
                id?.let { id -> movieClick.onClick(id) }
            }

        }

    }

    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardView = itemView.cv_movie
        var poster = itemView.iv_movie_poster
    }

}