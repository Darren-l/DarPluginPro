package com.dar.pluginmodule.services

import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.dar.pluginmodule.base.BaseService

/**
 * DarrenAdd20210128: 插件service
 *
 */
class PluginService : BaseService(){

    private var TAG: String = PluginService::class.java.name

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Toast.makeText(appService,"#### 服务启动成功 ！！！", Toast.LENGTH_LONG).show()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}