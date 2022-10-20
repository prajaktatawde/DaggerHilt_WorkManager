package com.example.codingassignmentmo.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.codingassignmentmo.retrofit.RetroRepository
import com.example.codingassignmentmo.utils.API_QUERY
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

@HiltWorker
class RepoUpdater @AssistedInject constructor(
    @Assisted val appContext: Context,
    @Assisted val workerParams: WorkerParameters,
     private val repository: RetroRepository): CoroutineWorker(appContext,workerParams) {


    override suspend fun doWork(): Result = coroutineScope{
            withContext(Dispatchers.IO) {
                try {
                    val reposData = repository.makeApiCall(API_QUERY)
                    repository.saveData(reposData)
                    return@withContext Result.success()
                } catch (e: Exception) {
                    return@withContext Result.failure()
                }
            }

        }
}
