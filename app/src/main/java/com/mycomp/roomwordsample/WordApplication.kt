package com.mycomp.roomwordsample

import android.app.Application
import androidx.lifecycle.ViewModelProviders
import androidx.room.Room
import com.mycomp.roomwordsample.data.db.WordDao
import com.mycomp.roomwordsample.data.db.WordRepository
import com.mycomp.roomwordsample.data.db.WordRoomDatabase
import com.mycomp.roomwordsample.ui.WordViewModel
import com.mycomp.roomwordsample.ui.WordViewModelFactory
import com.mycomp.roomwordsample.utilities.LogA
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WordApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@WordApplication))

        println("Bind calling")
        //Bind the Database
        bind<WordRoomDatabase>() with singleton { WordRoomDatabase.getDatabase(instance()) }

        bind<WordDao>() with singleton { instance<WordRoomDatabase>().wordDao() }

        bind<WordRepository>() with singleton {
            WordRepository(
                instance()
            )
        }

        bind() from provider { WordViewModelFactory(instance()) }
    }

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate() {
        super.onCreate()
        println("I am being created")

        initializeLogging()
    }

    override fun onTerminate() {
        super.onTerminate()

        println("I am terminator")
    }

    private fun initializeLogging() {

        println("Initialize Logging Called")
        val logViewModelFactory: WordViewModelFactory by instance()


        println("initialize logging called logViewModelFactory Instance: $logViewModelFactory")
//        val wordRoomDatabase = Room.inMemoryDatabaseBuilder(this, WordRoomDatabase::class.java).build()
//        val wordDao = wordRoomDatabase.wordDao()
//        val wordRepo = WordRepository(wordDao)

        wordViewModel = logViewModelFactory.create(WordViewModel::class.java)

        println("initialize logging called wordViewModel Instance: $wordViewModel")
        //wordViewModel = WordViewModel(wordRepo)
        LogA.setLogViewModel(wordViewModel)

        LogA.purgeOldLogsGreaterThan(7)

    }
}