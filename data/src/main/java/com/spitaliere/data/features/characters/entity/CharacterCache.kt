package com.spitaliere.data.features.characters.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.database.converters.StringListConverter

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
@Entity(tableName = "characters")
data class CharacterCache(
    @PrimaryKey
    val id : String,
    val name : String,
    val description : String,
    val thumbnail : String,
    @TypeConverters(StringListConverter::class)
    val comics : List<String>
)