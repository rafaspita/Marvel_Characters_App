package com.spitaliere.domain.features.comics.model

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
data class ComicsInfo(
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