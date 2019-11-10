package com.spitaliere.domain.features.characters.model

import java.io.Serializable

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
class CharacterInfo(
    val id : String,
    val name : String,
    val description : String,
    val thumbnail : String,
    val comics : List<String>
) : Serializable