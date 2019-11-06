package com.spitaliere.data.features.characters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.characters.entity.CharacterCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(characters: List<CharacterCache>)

    @Query("SELECT * FROM characters WHERE id IN (:charactersId) ORDER BY name ASC")
    fun getCharactersIn(charactersId: List<String>) : Single<List<CharacterCache>>

}