package com.dar.testplug.proxy

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.dar.plugmid.interfaces.SerCallback
import com.dar.testplug.manager.PlugMn

/**
 * DarrenAdd20210128：代理服务。
 *
 */

class ProxyService: Service() {

    override fun getClassLoader(): ClassLoader {
        return PlugMn.dexClassLoader!!
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onCreate() {
        super.onCreate()
    }

    /**
     * DarrenNote20210128
     *
     *  策略与act跳转相同，先获取插件service对象，注入代理service环境，同步插件service生命周期。
     *
     *
     */
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //1. 获取插件service
        val SerClassName = intent!!.getStringExtra("className")

        val mPluginActivity = PlugMn.getClassObj(SerClassName)
        mPluginActivity as SerCallback

        //2. 注入环境
        mPluginActivity.insertAppContext(this)

        //3. 同步生命周期
        mPluginActivity.onStartCommand(intent,flags,startId)

        return super.onStartCommand(intent, flags, startId)
    }
}