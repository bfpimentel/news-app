package dev.pimentel.news

import android.app.Application
import dev.pimentel.core.coreModules
import org.koin.core.context.loadKoinModules

@Suppress("Unused")
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoinModules(coreModules)
    }
}
