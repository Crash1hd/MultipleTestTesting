package com.mycomp.roomwordsample

import android.annotation.SuppressLint
import android.app.Application
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware

@SuppressLint("Registered")
class TestWordApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {

    }

    override fun onCreate() {
        super.onCreate()
        println("I am being created")

    }

    override fun onTerminate() {
        super.onTerminate()

        println("I am terminator")
    }

}