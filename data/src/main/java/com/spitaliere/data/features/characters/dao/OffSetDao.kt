package com.spitaliere.data.features.characters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spitaliere.data.features.characters.entity.OffSetCache
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 05/11/2019.
 **/
@Dao
interface OffSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(offSetCache: OffSetCache)

    @Query("SELECT * FROM off_set WHERE id = :offSetId")
    fun getOffSet(offSetId: String) : Single<OffSetCache>

}