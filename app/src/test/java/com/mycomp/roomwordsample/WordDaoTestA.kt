package com.mycomp.roomwordsample

import androidx.room.Room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mycomp.roomwordsample.data.db.WordDao
import com.mycomp.roomwordsample.data.db.WordRoomDatabase
import com.mycomp.roomwordsample.data.utilities.getValue
import org.hamcrest.Matchers.equalTo
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//@RunWith(RobolectricTestRunner::class)
class WordDaoTestA {
    private lateinit var wordRoomDatabase: WordRoomDatabase
    private lateinit var wordDao: WordDao

    @get:Rule
    var instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    @Throws(Exception::class)
    fun createDb() {
        println("Opening test DB")
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        wordRoomDatabase = Room.inMemoryDatabaseBuilder(context, WordRoomDatabase::class.java).build()
        wordDao = wordRoomDatabase.wordDao()

        wordRoomDatabase.wordDao().insertAll(testWords)
    }

    @After
    @Throws(Exception::class)
    fun closeDb() {
        println("Closing test db")
        wordRoomDatabase.close()
    }

    @Test
    fun testAAA() {
        println("Test One")
        Assert.assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
        println("End Test One")
    }

    @Test
    fun testBBB() {
        println("Test Two")
        Assert.assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
        println("End of Test Two")
    }

    @Test
    fun testCCC() {
        println("Test 3")
        Assert.assertThat(getValue(wordDao.getAllLiveWords()).size, equalTo(3))
        println("End of Test 3")
    }

//    @Test fun insertAndGetUser() = runBlocking {
//        // Given a User that has been inserted into the DB
//        userDao.insertUser(user)
//
//        // When getting the Users via the DAO
//        val usersFromDb = userDao.getUsers()
//
//        // Then the retrieved Users matches the original user object
//        assertEquals(listOf(user), userFromDb)
//    }
}