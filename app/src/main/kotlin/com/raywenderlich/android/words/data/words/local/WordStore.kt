package com.raywenderlich.android.words.data.words.local

import androidx.paging.*
import com.raywenderlich.android.words.data.words.AppDatabase
import com.raywenderlich.android.words.data.words.Word
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

class WordStore(database: AppDatabase) {

    private val words = database.words

    private fun pagingWord(
        block :() -> PagingSource<Int, LocalWord>,
    ) = Pager(PagingConfig(pageSize = 20)) {block()}.flow
            .map { page -> page.map { localWord: LocalWord -> localWord.fromLocal() } }

    //fun all(): List<Word> = words.queryAll().map { it.fromLocal() }

    fun all() = pagingWord { words.queryAll() }

    fun all(term: String) = pagingWord { words.searchAll(term) }

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