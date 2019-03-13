package com.mycomp.roomwordsample.utilities

import android.util.Log.i
import com.mycomp.roomwordsample.ui.WordViewModel

object LogA {
    private lateinit var wordViewModel: WordViewModel

    fun setLogViewModel(lvm: WordViewModel) {
        wordViewModel = lvm
    }

    fun purgeOldLogsGreaterThan(XDays: Int) {
        println("Purging Database Before")

        val foo = wordViewModel.deleteAllLogsOlderThanA(XDays)
        i("Log", "Purging $foo logs which where older than $XDays old")
        println("Purging Database After")
    }
}