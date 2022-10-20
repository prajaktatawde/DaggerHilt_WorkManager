package com.example.codingassignmentmo.retrofit

import androidx.lifecycle.LiveData
import com.example.codingassignmentmo.localdata.AppDao
import com.example.codingassignmentmo.model.RepoListModel
import com.example.codingassignmentmo.model.ReposData
import javax.inject.Inject


//make the api call and store the data inside room database
class RetroRepository @Inject constructor(
    private val retroServiceInterface: RetroServiceInterface,
    private val appDao: AppDao
) {

    fun getAllReposList(): LiveData<List<ReposData>> {
        return appDao.getAllRepos()
    }

    fun saveData(reposData: RepoListModel) {
        appDao.deleteAllReocrds()
        appDao.insertAllRecords(reposData.items)
    }

    //get data from api
    suspend fun makeApiCall(query: String?): RepoListModel {
        return retroServiceInterface.getDataFromAPI(query!!)
    }
}
