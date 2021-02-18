package com.dar.pluginmodule.player

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.dar.plugmid.interfaces.IPlayer

class BasePlayer: IPlayer {
    private var TAG:String = BasePlayer::class.java.name

    var context:Activity ?= null
    override fun start() {
        Log.d(TAG,"####BasePlayer, start()...")

//        Toast.makeText(context,"#### 播放器出错 ！！！",Toast.LENGTH_LONG).show()

        Toast.makeText(context,"#### 播放器bug已修复 ！！！",Toast.LENGTH_LONG).show()

    }

    override fun stop() {
    }

    override fun pause() {
    }

    override fun insertAppContext(appActivity: Activity) {
        this.context = appActivity
    }
}