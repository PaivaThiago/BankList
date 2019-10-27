package paiva.thiago.banklist.util

import android.app.Application
import paiva.thiago.banklist.BuildConfig
import timber.log.Timber


class BankApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}