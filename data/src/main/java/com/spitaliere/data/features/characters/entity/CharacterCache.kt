package com.spitaliere.data.features.characters.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.database.converters.StringListConverter
import com.spitaliere.domain.features.characters.model.CharacterInfo

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

//mappers
fun List<CharacterCache>.mapCharacterToDomain() = map { it.mapToDomain() }

fun CharacterCache.mapToDomain() = CharacterInfo(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail,
    comics  = comics
)