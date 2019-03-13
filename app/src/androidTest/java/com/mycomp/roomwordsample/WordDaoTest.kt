package com.mycomp.roomwordsample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.espresso.matcher.ViewMatchers.assertThat

import androidx.test.platform.app.InstrumentationRegistry
import com.mycomp.roomwordsample.data.db.WordDao
import com.mycomp.roomwordsample.data.db.WordRoomDatabase
import com.mycomp.roomwordsample.data.utilities.getValue
import org.hamcrest.Matchers.equalTo
import org.junit.*

class WordDaoATest {
    private lateinit var wordRoomDatabase: WordRoomDatabase
    private lateinit var wordDao: WordDao

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        wordRoomDatabase = Room.inMemoryDatabaseBuilder(context, WordRoomDatabase::class.java).build()
        wordDao = wordRoomDatabase.wordDao()

        wordRoomDatabase.wordDao().insertAll(testWords1)
    }

    @After
    fun closeDb() {
        wordRoomDatabase.close()
    }

    @Test
    fun testGetName() {
        assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
    }

    @Test
    fun testGetName1() {
        assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
    }

    @Test
    fun testGetName2() {
        assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
    }
}