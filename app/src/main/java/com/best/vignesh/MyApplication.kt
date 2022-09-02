package com.best.vignesh

import android.app.Application
import android.content.Context
import com.best.vignesh.model.AppDatabase
import com.best.vignesh.model.ProfileInfo
import kotlinx.coroutines.CoroutineScope

class MyApplication : Application() {

    lateinit var appContext : Context
    //lateinit var db : AppDatabase
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        //db = AppDatabase(this)


    }



}