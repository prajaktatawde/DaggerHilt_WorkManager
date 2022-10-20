package com.example.codingassignmentmo.model

import android.os.Parcelable
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.parcelize.Parcelize
import java.lang.reflect.Type

@Parcelize
data class Owner(val avatar_url: String,
                 val events_url: String,
                 val followers_url: String,
                 val following_url: String,
                 val gists_url: String,
                 val gravatar_id: String,
                 val html_url: String,
                 val id: Int,
                 val login: String,
                 val node_id: String,
                 val organizations_url: String,
                 val received_events_url: String,
                 val repos_url: String,
                 val site_admin: Boolean,
                 val starred_url: String,
                 val subscriptions_url: String,
                 val type: String,
                 val url: String):Parcelable


class TypeConverterOwner{
    //helps to store the object to string in one column in database
    // inshort helps to convert object to string
    val gson : Gson = Gson()
    @TypeConverter
    fun stringToSomeObjectList(data: String?): Owner?{
        if(data == null)return null
        val listType : Type = object :TypeToken<Owner?>(){}.type
        return gson.fromJson<Owner?>(data,listType)
    }


    //vice versa from string to object
    @TypeConverter
    fun someObjectListToString(someobject: Owner?): String?{
        return gson.toJson(someobject)
    }
}

