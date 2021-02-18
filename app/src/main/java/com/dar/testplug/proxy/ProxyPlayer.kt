package com.dar.testplug.proxy

import android.app.Activity
import com.dar.plugmid.interfaces.IPlayer
import com.dar.testplug.manager.PlugMn


/**
 * DarrenAdd20210128：代理module对象测试。
 *
 */
class ProxyPlayer constructor(actContext: Activity) {

    private var act:Activity ?= null
    init {
        this.act = actContext
    }

    fun callPlugFun(){

        val mPluginActivity = PlugMn.getClassObj("com.dar.pluginmodule.player.BasePlayer")

        mPluginActivity as IPlayer

        mPluginActivity.insertAppContext(act!!)

        mPluginActivity.start()
    }

}