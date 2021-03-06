package com.mycomp.roomwordsample.data.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class WordRepository(private val wordDao: WordDao): WordRepo {

    override val allWords: LiveData<List<Word>> = wordDao.getAllLiveWords()

    @WorkerThread
    override suspend fun insertAll(words: List<Word>): List<Long> = withContext(IO) {
        println("Insert All in Word Repo is called")
        //val insertAll = wordDao.insertAll(words)
        return@withContext wordDao.insertAll(words)
    }

    @WorkerThread
    override suspend fun deleteAllLogsOlderThan(XDays: Int): Int = withContext(IO) {
        print("Calling Delete All Logs Older Than In Repo: $XDays \n")
        return@withContext wordDao.deleteAll()
    }
}