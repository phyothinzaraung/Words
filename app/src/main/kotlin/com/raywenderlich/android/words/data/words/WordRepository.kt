package com.raywenderlich.android.words.data.words

import com.raywenderlich.android.words.data.words.local.WordStore
import com.raywenderlich.android.words.data.words.remote.WordSource

class WordRepository(private val wordSource: WordSource,
                     private val wordStore: WordStore) {

    constructor(database: AppDatabase): this(
        wordSource = WordSource(),
        wordStore = WordStore(database)
    )

    //suspend fun allWords(): List<Word> = wordSource.load()
    suspend fun allWords(): List<Word> = wordStore.ensureIsNotEmpty().all()

    private suspend fun WordStore.ensureIsNotEmpty() = apply {
        if(isEmpty()){
            val words = wordSource.load()
            save(words)
        }
    }
}