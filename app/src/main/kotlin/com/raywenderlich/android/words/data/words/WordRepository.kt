package com.raywenderlich.android.words.data.words

import androidx.paging.PagingData
import com.raywenderlich.android.words.data.words.local.WordStore
import com.raywenderlich.android.words.data.words.remote.WordSource
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordSource: WordSource,
                     private val wordStore: WordStore) {

    constructor(database: AppDatabase): this(
        wordSource = WordSource(),
        wordStore = WordStore(database)
    )

    //suspend fun allWords(): List<Word> = wordSource.load()
    suspend fun allWords(): Flow<PagingData<Word>> = wordStore.ensureIsNotEmpty().all()

    suspend fun allWords(term: String): Flow<PagingData<Word>> = wordStore.ensureIsNotEmpty().all(term)

    private suspend fun WordStore.ensureIsNotEmpty() = apply {
        if(isEmpty()){
            val words = wordSource.load()
            save(words)
        }
    }
}