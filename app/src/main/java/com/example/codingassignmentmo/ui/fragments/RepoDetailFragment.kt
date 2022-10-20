package com.example.codingassignmentmo.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.codingassignmentmo.BR
import com.example.codingassignmentmo.databinding.FragmentRepoDetailBinding
import com.example.codingassignmentmo.model.ReposData
import com.example.codingassignmentmo.viewmodel.RepoDetailViewModel

class RepoDetailFragment : Fragment() {

    private lateinit var fragmentRepoDetailBinding: FragmentRepoDetailBinding
    private val args: RepoDetailFragmentArgs by navArgs()
    lateinit var viewModel : RepoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val repoDetails = args.reposData
        viewModel = initViewModel()
        setUpData(repoDetails)
        setupBinding(inflater,viewModel)

        return fragmentRepoDetailBinding.root
    }

    private fun initViewModel(): RepoDetailViewModel {
        val viewModel = ViewModelProvider(this).get(RepoDetailViewModel::class.java)
        return viewModel
    }

    private fun setUpData(repoDetails: ReposData) {
        viewModel.repoDetailData.value = repoDetails
    }

    private fun setupBinding(inflater: LayoutInflater, repoDetailViewModel: RepoDetailViewModel) {
        fragmentRepoDetailBinding = FragmentRepoDetailBinding.inflate(inflater)
        fragmentRepoDetailBinding.setVariable(BR.repoDetailViewModel,repoDetailViewModel)
        fragmentRepoDetailBinding.repoDetailViewModel = viewModel
        fragmentRepoDetailBinding.executePendingBindings()
    }

}