package com.dar.testplug.proxy

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import com.dar.plugmid.interfaces.ActCallback
import com.dar.testplug.manager.PlugMn

/**
 * DarrenAdd20210126:代理Act。利用代理的Act环境给到插件Act，在生命周期里同步插件的生命周期。
 *
 */
class ProxyActivity : Activity() {
    private val TAG = ProxyActivity::class.java.name

    /**
     * 替换插件的classLoader。
     *
     */
    override fun getClassLoader(): ClassLoader {
        return PlugMn.dexClassLoader!!
    }

    /**
     * 替换插件的资源。
     *
     */
    override fun getResources(): Resources {
        return PlugMn.resources!!
    }


    /**
     * DarrenAdd20210126：替换执行代理的那个act oncreate。
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_proxy)

        try {
            /**
             * DarrenNote:
             * 1. 从intent中获取到实际要跳转的插件Act。
             */

            var className = intent.getStringExtra("className")

            /**
             * 2. 实例化插件act，实例化后注入，目的是将代理的act环境传递给插件act。
             */
            val mPluginActivity = PlugMn.getClassObj(className)

            var activityinter = mPluginActivity as ActCallback
            activityinter.insertAppContext(this)

            /**
             * 3. 同步插件的生命周期。
             */
            val bundle = Bundle()
            bundle.putString("app", "mainAct...")
            activityinter.onCreate(bundle)

        }catch (e: Exception){
            e.printStackTrace()
            Log.e(TAG, "onCreate error... ")
        }
    }

    /**
     * DarrenNote
     *
     *  策略：
     *
     *    不管是哪个页面，都通过ProxyActivity入栈，再通过ProxyActivity生命周期来同步要加载的插件页面。
     *
     */
    override fun startActivity(intent: Intent?) {
        val className = intent!!.getStringExtra("className")
        val proxyIntent = Intent(this, ProxyActivity::class.java)
        proxyIntent.putExtra("className", className)

        //再使用ProxyActivity进栈。
        super.startActivity(proxyIntent)
    }


    override fun startService(intent: Intent): ComponentName? {
        val className = intent.getStringExtra("className")

        val intentSer = Intent(this, ProxyService::class.java)
        intentSer.putExtra("className", className)
        return super.startService(intentSer)
    }

}