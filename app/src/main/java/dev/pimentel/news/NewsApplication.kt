package dev.pimentel.news

import android.app.Application
import dev.pimentel.core.coreModules
import dev.pimentel.navigator.navigatorModules
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

@Suppress("Unused")
class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            loadKoinModules(coreModules + navigatorModules)
        }
    }
}
