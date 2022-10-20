package com.example.codingassignmentmo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.codingassignmentmo.model.ReposData

class RepoDetailViewModel(application: Application) : AndroidViewModel(application) {

    val repoDetailData : MutableLiveData<ReposData> = MutableLiveData()

}