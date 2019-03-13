package com.mycomp.roomwordsample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycomp.roomwordsample.data.db.Word
import com.mycomp.roomwordsample.data.db.WordRepository
import kotlinx.coroutines.*

class WordViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val allWords: LiveData<List<Word>> = wordRepository.allWords

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        println("On Cleared in WordViewModel is called")
        viewModelScope.cancel()
    }

    fun insertAllNORETURN(words: List<Word>) {
        viewModelScope.launch {
            wordRepository.insertAll(words)
        }
    }

    fun insertAll(words: List<Word>): List<Long> = runBlocking {
        //return viewModelScope.launch {
            wordRepository.insertAll(words)
        //}
    }

    fun deleteAllLogsOlderThanA(XDays:Int): Int = runBlocking {
        println("Delete All Logs Older Than A in WordViewModel is called")
        wordRepository.deleteAllLogsOlderThan(XDays)
    }

//    fun deleteAllLogsOlderThanB(XDays:Int) {
//        viewModelScope.launch {
//            wordRepository.deleteAllLogsOlderThan(XDays)
//        }
//    }

//}
}