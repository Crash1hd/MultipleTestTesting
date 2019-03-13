package com.mycomp.roomwordsample.data.db

import androidx.annotation.WorkerThread
import com.mycomp.roomwordsample.data.db.Word

interface WordRepo {

    @WorkerThread
    suspend fun insertAll(words: List<Word>): List<Long>

}
