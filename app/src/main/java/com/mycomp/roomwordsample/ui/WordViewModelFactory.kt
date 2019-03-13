package com.mycomp.roomwordsample.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycomp.roomwordsample.data.db.WordRepository

// The same repository that's needed for WordViewModel
// is also passed to the factory
class WordViewModelFactory(private val wordRepository: WordRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var instance: WordViewModel? = null

        instance ?: synchronized(Any()) {
            instance ?: WordViewModel(wordRepository).also {
                instance = it
            }
        }

        println("word view factory return $instance")
        return instance as T

        //return WordViewModel(wordRepository) as T
    }
}