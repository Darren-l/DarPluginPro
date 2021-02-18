package com.dar.plugmid.interfaces

import android.app.Service
import android.content.Intent


/**
 * DarrenAdd20210128：
 *
 *  抽象service生命周期。
 *
 */
interface SerCallback {
    fun insertAppContext(appService: Service?)

    fun onCreate()

    fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int

    fun onDestroy()
}