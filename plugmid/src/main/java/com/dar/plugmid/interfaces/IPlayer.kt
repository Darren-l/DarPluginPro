package com.dar.plugmid.interfaces

import android.app.Activity

/**
 *  DarrenAdd20210128：测试站桩式策略可行性。
 *
 */
interface IPlayer {
    fun start()
    fun stop()
    fun pause()

    fun insertAppContext(appActivity: Activity)
}