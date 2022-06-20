package com.raywenderlich.android.words.data.words

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raywenderlich.android.words.data.words.local.LocalWord
import com.raywenderlich.android.words.data.words.local.WordDao

@Database(entities = [LocalWord::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract val words: WordDao
}