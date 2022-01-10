package de.mtgCompanion.android

import android.app.Application
import de.mtgCompanion.shared.services.AppService

class MyApplication : Application() {
    // global available Variable/Values
    companion object {
        val appService = AppService()
    }
}