package com.spitaliere.data.features.comics.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.comics.entity.ComicsCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
@Dao
interface ComicsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(comicsCache: ComicsCache)

    @Query("SELECT * FROM comics WHERE characterId = :characterId")
    fun getComicsByCharacterId(characterId:String) : Single<ComicsCache>
}