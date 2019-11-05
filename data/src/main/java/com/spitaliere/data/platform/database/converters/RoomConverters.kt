package com.spitaliere.data.platform.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spitaliere.data.features.comics.entity.ComicsInfoCache
import java.lang.reflect.Type

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/

class ComicsInfoConverter{
    val type: Type = object : TypeToken<List<ComicsInfoCache>>() {}.type

    @TypeConverter
    fun fromList(list: List<ComicsInfoCache>) : String = Gson().toJson(list, type)

    @TypeConverter
    fun toList(items: String): List<ComicsInfoCache> = Gson().fromJson(items, type)
}

class StringListConverter{
    val type: Type = object : TypeToken<List<String>>() {}.type

    @TypeConverter
    fun fromList(list: List<String>) : String = Gson().toJson(list, type)

    @TypeConverter
    fun toList(items: String): List<String> = Gson().fromJson(items, type)
}