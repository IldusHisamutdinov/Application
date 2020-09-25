package com.example.application.ui

import android.app.Application
import com.example.application.di.appModule
import com.example.application.di.mainModule
import com.example.application.di.noteModule
import com.example.application.di.splashModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    companion object {
	    lateinit var instance: App
		    private set
	}
	
	override fun onCreate() {
	    super.onCreate()
		instance = this
		startKoin(this, listOf(appModule, splashModule, mainModule, noteModule))
	}
}