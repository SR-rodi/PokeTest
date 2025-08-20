package ru.sr.poketest.presentation.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.sr.poketest.di.pokeModule

class PokeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokeApplication)
            modules(
                listOf(pokeModule())
            )
        }
    }
}
