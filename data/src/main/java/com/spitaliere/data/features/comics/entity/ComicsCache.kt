package com.spitaliere.data.features.comics.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.spitaliere.data.platform.database.converters.ComicsInfoConverter
import com.spitaliere.domain.features.comics.model.ComicsInfo

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
@Entity(tableName = "comics")
data class ComicsCache(
    @PrimaryKey
    val characterId : String,
    @TypeConverters(ComicsInfoConverter::class)
    val comicsInfo : List<ComicsInfoCache>
)

data class ComicsInfoCache(
    val id : String,
    val digitalId : String,
    val title : String,
    val issueNumber : String,
    val variantDescription : String,
    val description : String,
    val format : String,
    val pageCount : String,
    val thumbnail : String
)

//mappers
fun List<ComicsInfoCache>.mapComicsToDomain() : List<ComicsInfo> = map { it.mapToDomain() }

fun ComicsInfoCache.mapToDomain() : ComicsInfo = ComicsInfo(
    id = id,
    digitalId = digitalId,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description,
    format = format,
    pageCount = pageCount,
    thumbnail  = thumbnail
)

