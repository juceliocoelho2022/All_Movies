package com.devjucelio.allmovies.ui.mylist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.devjucelio.allmovies.MainActivity
import com.devjucelio.allmovies.R
import com.devjucelio.allmovies.dto.MovieDTO
import com.devjucelio.allmovies.ui.GridAdapter
import com.devjucelio.allmovies.ui.OnMovieClick
import com.devjucelio.allmovies.viewmodel.HomeViewModel
import com.devjucelio.allmovies.viewmodel.MyListViewModel
import javax.inject.Inject

class MyListFragment : Fragment(), OnMovieClick {
    private val onItemClick = this as OnMovieClick
    private lateinit var recyclerView: RecyclerView
//    private lateinit var viewModel: MyListViewModel
    private lateinit var adapter: GridAdapter
    private var adViewBottomRv: AdView? = null
    private var adViewBottomScreen: AdView? = null

    // Dagger code
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<MyListViewModel> { viewModelFactory }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as MainActivity).mainComponent.inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_mylist, container, false)
        val progressBar: ProgressBar = root.findViewById(R.id.my_list_progress_circular)

        Log.d("myadlog", "view = $view")
        Log.d("myadlog", "view = ${view.toString()}")

        adViewBottomRv = root.findViewById(R.id.mylist_adview_bottom_rv)
        adViewBottomScreen = root.findViewById(R.id.mylist_adview_bottom_screen)

        val emptyList = root.findViewById<TextView>(R.id.edt_my_list_empty)
        emptyList.visibility = View.VISIBLE

        progressBar.visibility = View.VISIBLE
        adapter = GridAdapter(context, onItemClick, ArrayList())
        recyclerView = root.findViewById(R.id.my_list_list)

        setUpRecyclerView()
//        viewModel =
//            ViewModelProviders.of(this).get(MyListViewModel::class.java)
        viewModel.myList.observe(viewLifecycleOwner, Observer { list ->
            Log.d("receivertest", "onChange, list.size = ${list.size}")
            adapter.setList(list)

            if (list.isNullOrEmpty()) {
                emptyList.visibility = View.VISIBLE
                makeAdsInvisible()
            }
            else {
                emptyList.visibility = View.INVISIBLE
                setAdsVisibility(list)
            }

            progressBar.visibility = View.INVISIBLE
        })

        loadAdsBottomRv()
        loadAdsBottomScreen()

        return root
    }

    private fun makeAdsInvisible() {
        adViewBottomRv?.visibility = View.INVISIBLE
        adViewBottomScreen?.visibility = View.INVISIBLE
    }

    private fun setAdsVisibility(list: List<MovieDTO>) {
        if (list.size >= 7){
            adViewBottomRv?.visibility = View.VISIBLE
            adViewBottomScreen?.visibility = View.INVISIBLE
        } else {
            adViewBottomRv?.visibility = View.INVISIBLE
            adViewBottomScreen?.visibility = View.VISIBLE
        }
    }

    fun loadAdsBottomRv() {
        if (context != null) {
            MobileAds.initialize(context) {}
            val adRequest = AdRequest.Builder().build()
            adViewBottomRv?.loadAd(adRequest)
        }
    }

    fun loadAdsBottomScreen() {
        if (context != null) {
            MobileAds.initialize(context) {}
            val adRequest = AdRequest.Builder().build()
            adViewBottomScreen?.loadAd(adRequest)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).showRatingDialog()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun setUpRecyclerView() {
        recyclerView.computeVerticalScrollOffset()
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = adapter
    }

    override fun onClick(id: Int) {
        val myListToDetailsFragment
                = MyListFragmentDirections.actionNavigationMyListToDetailsFragment(id)
        findNavController().navigate(myListToDetailsFragment)
    }

//    @Test
//    fun testNavigationToInGameScreen() {
//        // Create a mock NavController
////        val mockNavController = mock(NavController::class.java)
////
////        // Create a graphical FragmentScenario for the TitleScreen
////        val titleScenario = launchFragmentInContainer<MyListFragment>()
////
////        // Set the NavController property on the fragment
////        titleScenario.onFragment { fragment ->
////            Navigation.setViewNavController(fragment.requireView(), mockNavController)
////        }
////
////        // Verify that performing a click prompts the correct Navigation action
////        onView(ViewMatchers.withId(R.id.play_btn)).perform(ViewActions.click())
////        verify(mockNavController).navigate(R.id.action_title_screen_to_in_game)
//    }

}