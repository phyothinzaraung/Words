package com.raywenderlich.android.words.data.words.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("select * from word order by value")
    fun queryAll(): List<LocalWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(words: List<LocalWord>)

    @Query("select count(*) from word")
    suspend fun count(): Long
}