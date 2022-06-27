package com.raywenderlich.android.words.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.raywenderlich.android.words.data.words.RandomWords
import com.raywenderlich.android.words.data.words.Word
import com.raywenderlich.android.words.ui.bars.MainTopBar
import com.raywenderlich.android.words.ui.bars.SearchBar
import kotlinx.coroutines.flow.Flow

@Composable
fun WordListUi(
    words: Flow<PagingData<Word>>,
    search: String?,
    onSearch: (String?) -> Unit
){
    Scaffold(topBar = {
        SearchBar(search = search, onSearch = onSearch)
                      },
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
            modifier = Modifier.padding(16.dp).fillMaxWidth(),           // 3
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
    words: Flow<PagingData<Word>>,
    onSelected: (Word) -> Unit,
) {
    val items: LazyPagingItems<Word> = words.collectAsLazyPagingItems()
    LazyColumn {
        if (items.itemCount == 0){
            item { EmptyContent() }
        }
        items(items = items) { word ->
            if (word != null) {
                WordColumnItem(
                    word = word
                ) { onSelected(word) }
            }
        }
    }
}

@Composable
private fun LazyItemScope.EmptyContent(){
    Box(modifier = Modifier.fillParentMaxSize(),
    contentAlignment = Alignment.Center){
        Text(text = "No matching word")
    }
}