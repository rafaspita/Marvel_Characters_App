package com.spitaliere.data.api.response

import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.comics.entity.ComicsInfoCache

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
data class ApiResponse<DATA : Data<*>>(
    val code : String,
    val status : String,
    val copyright : String,
    val attributionText : String,
    val attributionHTML : String,
    val data : DATA,
    val etag : String
)

data class Data<RESULTS>(
    val offset : String,
    val limit : String,
    val total : String,
    val count : String,
    val results : List<RESULTS>
)

data class CharacterResult (
    val id : String,
    val name : String,
    val description : String,
    val thumbnail : Thumbnail,
    val comics : Comics
)

data class ComicsResults (
    val id : String,
    val digitalId : String,
    val title : String,
    val issueNumber : String,
    val variantDescription : String,
    val description : String?,
    val format : String,
    val pageCount : String,
    val thumbnail : Thumbnail
)

data class Thumbnail (
    val path : String,
    val extension : String
){
    fun getImageUrl() = "$path.$extension".replace("http", "https")
}

data class Comics (
    val available : String,
    val returned : String,
    val collectionURI : String,
    val items : List<Items>
)

data class Items (
    val resourceURI : String,
    val name : String
){
    fun getComicsId() = resourceURI.split("/").last()
}

//mappers
fun List<CharacterResult>.mapCharactersToCache() : List<CharacterCache> = map { it.mapToCache() }

fun CharacterResult.mapToCache() : CharacterCache = CharacterCache(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.getImageUrl(),
    comics = comics.items.map { it.getComicsId() }
)

fun List<ComicsResults>.mapComicsToCache() : List<ComicsInfoCache> = map { it.mapToCache() }

fun ComicsResults.mapToCache() : ComicsInfoCache = ComicsInfoCache(
    id = id,
    digitalId = digitalId,
    title = title,
    issueNumber = issueNumber,
    variantDescription = variantDescription,
    description = description ?: "No Description",
    format = format,
    pageCount = pageCount,
    thumbnail  = thumbnail.getImageUrl()
)

