package com.example.codingassignmentmo

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyRepoApplication: Application(),Configuration.Provider {
    @Inject
    lateinit var repoUpdaterFactory: HiltWorkerFactory

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder().setWorkerFactory(repoUpdaterFactory).build()
    }


}


