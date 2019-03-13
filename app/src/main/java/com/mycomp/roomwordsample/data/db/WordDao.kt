package com.mycomp.roomwordsample.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mycomp.roomwordsample.data.db.Word

@Dao
interface WordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllLiveWords(): LiveData<List<Word>>

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAllWords(): List<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(word: List<Word>): List<Long>

    @Query("DELETE FROM word_table")
    fun deleteAll(): Int
}