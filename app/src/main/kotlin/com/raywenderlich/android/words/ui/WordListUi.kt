package com.raywenderlich.android.words.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.android.words.data.words.RandomWords
import com.raywenderlich.android.words.data.words.Word
import com.raywenderlich.android.words.ui.bars.MainTopBar

@Composable
fun WordListUi(words: List<Word>){
    Scaffold(topBar = {MainTopBar()},
    content = {
        WordsContent(
            //words = RandomWords.map { Word(it) },
            words = words,
            onSelected = {word -> Log.e("WordContent", "Selected: $word") })
    })
}

@Composable
private fun WordColumnItem(
    word: Word,
    onClick: () -> Unit,
) {
    Row(                                              // 1
        modifier = Modifier.clickable { onClick() },    // 2
    ) {
        Text(
            modifier = Modifier.padding(16.dp),           // 3
            text = word.value,                            // 4
        )
    }
}

//@Composable
//private fun WordsContent(
//    words: List<Word>,
//    onSelected: (Word) -> Unit,
//) {
//    LazyColumn {              // 1
//        items(words) { word ->  // 2
//            WordColumnItem(     // 3
//                word = word
//            ) { onSelected(word) }
//        }
//    }
//}

@Composable
private fun WordsContent(
    words: List<Word>,
    onSelected: (Word) -> Unit
) {
    LazyColumn {
        items(words) { word ->
            WordColumnItem(
                word = word
            ) {
                onSelected(word)
            }
        }
    }
}
