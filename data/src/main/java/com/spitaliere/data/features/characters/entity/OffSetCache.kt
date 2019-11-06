package com.spitaliere.data.features.characters.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.database.converters.StringListConverter

/**
 * Created by Rafael Spitaliere on 04/11/2019.
 **/
@Entity(tableName = "off_set")
data class OffSetCache(
    @PrimaryKey
    val id: String,
    @TypeConverters(StringListConverter::class)
    val charactersId: List<String>
)