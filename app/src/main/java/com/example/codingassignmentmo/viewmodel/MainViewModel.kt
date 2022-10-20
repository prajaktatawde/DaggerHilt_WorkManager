package com.example.codingassignmentmo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingassignmentmo.model.ReposData
import com.example.codingassignmentmo.retrofit.RetroRepository
import com.example.codingassignmentmo.utils.API_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RetroRepository): ViewModel(){

    fun getAllRepositoryList(): LiveData<List<ReposData>>{
        return repository.getAllReposList()
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                if(repository.getAllReposList().value.isNullOrEmpty()){
                    val reposData = repository.makeApiCall(API_QUERY)
                    repository.saveData(reposData)
                }
            } catch (e: Exception) {
                Log.i("exception",e.toString())
            }
        }

    }
}