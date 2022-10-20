package com.example.codingassignmentmo.localdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codingassignmentmo.model.ReposData

@Dao
interface AppDao {

    @Query("SELECT * FROM repositories")
    fun getAllRepos(): LiveData<List<ReposData>>

    //if any conflicts happens it simply replace the record
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecords(reposData: ReposData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRecords(reposData: List<ReposData>)

    //first time we make api call and second time again if we call it then no duplication
    //so delete query will help in that
    @Query("DELETE FROM repositories")
    fun deleteAllReocrds()
}