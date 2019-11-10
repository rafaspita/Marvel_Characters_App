package com.spitaliere.domain.features.comics.model

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
data class ComicsInfo(
    var id : String = "",
    var digitalId : String = "",
    var title : String = "",
    var issueNumber : String = "",
    var variantDescription : String = "",
    var description : String = "",
    var format : String = "",
    var pageCount : String = "",
    var thumbnail : String = ""
)