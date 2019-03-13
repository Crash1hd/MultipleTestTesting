package com.mycomp.roomwordsample.data.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mycomp.roomwordsample.data.db.Word

interface WordRepo {

    val allWords: LiveData<List<Word>>

    @WorkerThread
    suspend fun insertAll(words: List<Word>): List<Long>

    @WorkerThread
    suspend fun deleteAllLogsOlderThan(XDays: Int): Int

}
