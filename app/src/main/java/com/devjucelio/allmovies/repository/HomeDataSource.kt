package com.devjucelio.allmovies.repository

import com.devjucelio.allmovies.domainmodel.MyListItem
import com.devjucelio.allmovies.dto.MovieDTO
import com.devjucelio.allmovies.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher

interface HomeDataSource {
    suspend fun getListsOfMovies(dispatcher: CoroutineDispatcher, homeResultCallback: (result: NetworkResponse<List<List<MovieDTO>>, Error>) -> Unit)

    fun isTopMovieInDatabase(id: Int) : Boolean

    fun refresh()

    fun insert(myListItem: MyListItem)

    fun delete(id: Int)
}