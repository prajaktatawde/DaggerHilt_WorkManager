package com.example.codingassignmentmo.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "repositories")
@Parcelize
data class ReposData(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")var id: Int = 0,
    @ColumnInfo(name = "full_name")val full_name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "stargazers_count") val stargazers_count: String?,
    @ColumnInfo(name = "watchers_count") val watchers_count: String?,
    @ColumnInfo(name = "forks_count") val forks_count: String?,
    @ColumnInfo(name = "owner") val owner: Owner?): Parcelable