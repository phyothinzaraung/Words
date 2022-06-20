package com.raywenderlich.android.words.data.words.local

import com.raywenderlich.android.words.data.words.AppDatabase
import com.raywenderlich.android.words.data.words.Word

class WordStore(database: AppDatabase) {

    private val words = database.words

    fun all(): List<Word> = words.queryAll().map { it.fromLocal() }

    suspend fun save(words: List<Word>){
        this.words.insert(words.map { it.toLocal() })
    }

    suspend fun isEmpty(): Boolean = words.count() == 0L

    private fun Word.toLocal() = LocalWord(
        value = value
    )

    private fun LocalWord.fromLocal() = Word(
        value = value
    )
}