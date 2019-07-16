package com.android.kotlinproject

import timber.log.Timber
import android.app.Application


class TimberClass : Application() {
    override fun onCreate()
    {
        super.onCreate()
        if (BuildConfig.DEBUG)
        {
            Timber.plant(Timber.DebugTree())
        }
    }
}