package com.example.codingassignmentmo.ui.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.example.codingassignmentmo.R
import com.example.codingassignmentmo.databinding.FragmentRepoListBinding
import com.example.codingassignmentmo.ui.adapter.RecyclerViewAdapter
import com.example.codingassignmentmo.utils.UNIQUE_WORK_NAME
import com.example.codingassignmentmo.utils.UNIQUE_WORK_TAG
import com.example.codingassignmentmo.viewmodel.MainViewModel
import com.example.codingassignmentmo.worker.RepoUpdater
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class RepoListFragment : Fragment() {

    lateinit var fragmentRepoListBinding: FragmentRepoListBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainViewModel = initMainViewModel()
        setupBinding(inflater)
        setData()
        setWorkerManager()
        return fragmentRepoListBinding.root
    }

    private fun setData() {
        mainViewModel.getAllRepositoryList().observe(viewLifecycleOwner, Observer {
            fragmentRepoListBinding.progressBar.visibility = View.INVISIBLE

            if (!checkForInternet(requireContext())) {
                //no internet
                if(it!=null){
                    //but data is there stored
                    recyclerViewAdapter.setRepoListData(it)
                    recyclerViewAdapter.notifyDataSetChanged()
                    fragmentRepoListBinding.recyclerRepos.visibility = View.VISIBLE
                    fragmentRepoListBinding.noInternet.visibility = View.INVISIBLE
                }else{
                    //data is not stored
                    fragmentRepoListBinding.recyclerRepos.visibility = View.INVISIBLE
                    fragmentRepoListBinding.noInternet.visibility = View.VISIBLE
                    fragmentRepoListBinding.txtNoInternet.text = getString(R.string.no_internet)
                }

            } else {
                //internet present
                if(it!= null){
                    //data is not null
                    fragmentRepoListBinding.recyclerRepos.visibility = View.VISIBLE
                    fragmentRepoListBinding.noInternet.visibility = View.GONE
                    recyclerViewAdapter.setRepoListData(it)
                    recyclerViewAdapter.notifyDataSetChanged()
                }else{
                    //getting error while fetching
                    fragmentRepoListBinding.wifiImage.visibility = View.INVISIBLE
                    fragmentRepoListBinding.txtNoInternet.text = getString(R.string.error)
                }

            }
        })
        fragmentRepoListBinding.progressBar.visibility = View.VISIBLE
        mainViewModel.makeApiCall()
    }

    private fun setWorkerManager() {
        var workerConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        var work = PeriodicWorkRequestBuilder<RepoUpdater>(5, TimeUnit.MINUTES)
            .setConstraints(workerConstraints)
            .addTag(UNIQUE_WORK_TAG)
            .build()

        WorkManager.getInstance(requireContext().applicationContext)
            .enqueueUniquePeriodicWork(UNIQUE_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, work)

    }


    private fun setupBinding(inflater: LayoutInflater) {
        fragmentRepoListBinding = FragmentRepoListBinding.inflate(inflater)
        fragmentRepoListBinding.executePendingBindings()

        fragmentRepoListBinding.recyclerRepos.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val decoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
        }
    }


    private fun initMainViewModel(): MainViewModel {
        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        return mainViewModel
    }

    private fun checkForInternet(context: Context): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false
            // Representation of the capabilities of an active network.
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                // else return false
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}

