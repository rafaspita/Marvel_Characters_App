package com.spitaliere.data.platform.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spitaliere.data.features.characters.dao.CharacterDao
import com.spitaliere.data.features.characters.dao.OffSetDao
import com.spitaliere.data.features.characters.entity.CharacterCache
import com.spitaliere.data.features.characters.entity.OffSetCache
import com.spitaliere.data.features.comics.dao.ComicsDao
import com.spitaliere.data.features.comics.entity.ComicsCache
import com.spitaliere.data.platform.database.converters.ComicsInfoConverter
import com.spitaliere.data.platform.database.converters.StringListConverter

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
@Database(
    version = 1,
    entities = [
        CharacterCache::class,
        OffSetCache::class,
        ComicsCache::class
    ],
    exportSchema = true
)
@TypeConverters(value = [
    ComicsInfoConverter::class,
    StringListConverter::class
])
abstract class AppDataBase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun offSetDao(): OffSetDao
    abstract fun comicsDao(): ComicsDao

    companion object{
        private var instance: AppDataBase? = null

        @Synchronized
        fun createDataBase(context: Context) : AppDataBase{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java,
                    "Marvel_App_DataBase.db"
                ).build()
            }

            return instance as AppDataBase
        }
    }
}