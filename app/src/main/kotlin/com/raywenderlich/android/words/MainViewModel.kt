package com.raywenderlich.android.words

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.raywenderlich.android.words.data.words.RandomWords
import com.raywenderlich.android.words.data.words.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application): AndroidViewModel(application){

    //val words: List<Word> = RandomWords.map { Word(it) }

    private val wordRepository =
        getApplication<WordsApp>().wordRepository
    //val words: List<Word> = runBlocking { wordRepository.allWords() }
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _search = MutableStateFlow(null as String?)
    val search: StateFlow<String?> = _search
    private val _words = MutableStateFlow(emptyFlow<PagingData<Word>>())
    //val words: StateFlow<Flow<PagingData<Word>>> = _words
    @OptIn(ExperimentalCoroutinesApi::class)
    val words: StateFlow<Flow<PagingData<Word>>> =
        search
            .flatMapLatest { search -> words(search) }
            .stateInViewModel(initialValue = emptyFlow())
    private val allWords = MutableStateFlow(emptyFlow<PagingData<Word>>())
    private val searchWords = MutableStateFlow(emptyFlow<PagingData<Word>>())

    fun load() = effect {
        _isLoading.value = true
        allWords.value = wordRepository.allWords()
        _isLoading.value = false
    }

    private fun effect(block: suspend() -> Unit){
        viewModelScope.launch(Dispatchers.IO) { block() }
    }


//    fun search(term: String?){
//        _search.value = term
//    }

    private fun words(search: String?) = when {
        search.isNullOrEmpty() -> allWords
        else -> searchWords
    }

    private fun <T> Flow<T>.stateInViewModel(initialValue: T): StateFlow<T> =
        stateIn(scope = viewModelScope, started = SharingStarted.Lazily,
        initialValue = initialValue)

    fun search(term: String?) = effect {
        _search.value = term
        if(term != null){
            searchWords.value = wordRepository.allWords(term)
        }
    }
}