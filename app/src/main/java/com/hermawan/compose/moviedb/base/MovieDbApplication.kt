package com.hermawan.compose.moviedb.base

import android.app.Application
import com.hermawan.compose.moviedb.di.databaseModule
import com.hermawan.compose.moviedb.di.networkModule
import com.hermawan.compose.moviedb.di.repositoryModule
import com.hermawan.compose.moviedb.di.useCaseModule
import com.hermawan.compose.moviedb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level.NONE
import timber.log.Timber

class MovieDbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger(NONE)
            androidContext(this@MovieDbApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}