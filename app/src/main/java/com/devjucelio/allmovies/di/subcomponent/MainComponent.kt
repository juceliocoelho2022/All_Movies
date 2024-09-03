package com.devjucelio.allmovies.di.subcomponent

import com.devjucelio.allmovies.MainActivity
import com.devjucelio.allmovies.ui.cast.PersonDetailsFragment
import com.devjucelio.allmovies.ui.details.DetailsFragment
import com.devjucelio.allmovies.ui.home.HomeFragment
import com.devjucelio.allmovies.ui.mylist.MyListFragment
import com.devjucelio.allmovies.ui.search.SearchFragment
import dagger.Subcomponent

@Subcomponent(modules = [])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    // Cria tbm para todos os fragments em que se vai a injeção
    fun inject(fragment: HomeFragment)
    fun inject(fragment: DetailsFragment)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: MyListFragment)
    fun inject(fragment: PersonDetailsFragment)

}