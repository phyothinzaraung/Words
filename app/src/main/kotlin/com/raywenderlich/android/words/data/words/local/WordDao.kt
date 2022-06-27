package com.raywenderlich.android.words.data.words.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {

    @Query("select * from word order by value")
    fun queryAll(): PagingSource<Int, LocalWord>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(words: List<LocalWord>)

    @Query("select count(*) from word")
    suspend fun count(): Long

    @Query("select * from word where value like :term || '%' order by value")
    fun searchAll(term: String): PagingSource<Int, LocalWord>
}