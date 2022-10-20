package com.example.codingassignmentmo.retrofit

import com.example.codingassignmentmo.model.RepoListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroServiceInterface {

    @GET("repositories")
    suspend fun getDataFromAPI(@Query("q")query: String): RepoListModel
}