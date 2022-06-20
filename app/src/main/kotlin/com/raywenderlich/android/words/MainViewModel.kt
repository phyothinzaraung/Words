package com.raywenderlich.android.words

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raywenderlich.android.words.data.words.RandomWords
import com.raywenderlich.android.words.data.words.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(application: Application): AndroidViewModel(application){

    //val words: List<Word> = RandomWords.map { Word(it) }

    private val wordRepository =
        getApplication<WordsApp>().wordRepository
    //val words: List<Word> = runBlocking { wordRepository.allWords() }
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _words = MutableStateFlow(emptyList<Word>())
    val words: StateFlow<List<Word>> = _words

    fun load() = effect {
        _isLoading.value = true
        _words.value = wordRepository.allWords()
        _isLoading.value = false
    }

    private fun effect(block: suspend() -> Unit){
        viewModelScope.launch(Dispatchers.IO) { block() }
    }
}