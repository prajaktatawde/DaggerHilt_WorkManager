package com.example.codingassignmentmo.localdata

import android.content.Context
import androidx.room.*
import com.example.codingassignmentmo.model.ReposData
import com.example.codingassignmentmo.model.TypeConverterOwner
import com.example.codingassignmentmo.utils.DB_NAME


@Database(entities = [ReposData::class],version = 1, exportSchema = false)
@TypeConverters(TypeConverterOwner::class)
abstract class AppDatabase: RoomDatabase() {

    //define DAO
    abstract fun getAppDao():AppDao

    companion object{
        private var DB_INSTANCE: AppDatabase? = null

        fun getAppDBInstance(context : Context): AppDatabase{
            if(DB_INSTANCE == null){
                DB_INSTANCE = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE!!

        }
    }
}