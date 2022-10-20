package com.example.assignment.di

import android.content.Context
import com.example.codingassignmentmo.localdata.AppDao
import com.example.codingassignmentmo.localdata.AppDatabase
import com.example.codingassignmentmo.retrofit.RetroServiceInterface
import com.example.codingassignmentmo.utils.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    //function to return the instance of database
    @Singleton
    @Provides
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getAppDBInstance(context)
    }

    //function to return instance of dao
    @Provides
    @Singleton
    fun getAppDao(appDatabase: AppDatabase):AppDao{
        return appDatabase.getAppDao()
    }


    @Provides
    @Singleton
    fun getRetroInstance() : Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //function to return the instance of retroserviceinterface
    @Provides
    @Singleton
    fun getRetroServiceInterfaceInstance(retrofit: Retrofit): RetroServiceInterface{
        return retrofit.create(RetroServiceInterface::class.java)
    }

}