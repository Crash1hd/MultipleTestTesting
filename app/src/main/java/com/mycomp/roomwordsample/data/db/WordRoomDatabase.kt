package com.mycomp.roomwordsample.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Database(entities = [Word::class], version = 1)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            println("Getting Database Instance")
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                println("Returning existing database instance $INSTANCE")
                println("Is the temoInstance Open ${tempInstance.isOpen}")
                return tempInstance
            }
            println("Creating new database call instance")
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "Word_database"
                ).addCallback(WordDatabaseCallback()).build()
                INSTANCE = instance
                println("Instance: $INSTANCE")
                return instance
            }
        }
    }

    private class WordDatabaseCallback : RoomDatabase.Callback() {
        private var parentJob = Job()
        private val coroutineContext: CoroutineContext
            get() = parentJob + Dispatchers.Main

        private val scope: CoroutineScope = CoroutineScope(coroutineContext)

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            println("On Open is called")
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.wordDao())
                }
            }
        }

        fun populateDatabase(wordDao: WordDao) {
            println("Populate Database is called")
            wordDao.deleteAll()

            var word = Word("Hello")
            wordDao.insert(word)
            word = Word("World!")
            wordDao.insert(word)
        }
    }
}
