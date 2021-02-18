package com.dar.pluginmodule.base

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dar.plugmid.interfaces.SerCallback


/**
 * DarrenAdd20210128:
 *
 *  插件服务基类。
 *
 */
open class BaseService : SerCallback,Service() {
    var appService : Service ?= null

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun insertAppContext(appService: Service?) {
        this.appService = appService
    }


    override fun onCreate() {
    }

    override fun onDestroy() {}

}