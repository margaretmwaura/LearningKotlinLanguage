package com.android.kotlinproject.Presenter

import timber.log.Timber
import android.app.Application
import com.android.kotlinproject.BuildConfig


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