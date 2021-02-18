package com.dar.pluginmodule.activitys

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dar.pluginmodule.R
import com.dar.pluginmodule.base.BaseActivity
import com.dar.pluginmodule.base.BaseService
import com.dar.pluginmodule.broadcast.PluginReceive
import com.dar.pluginmodule.services.PluginService
import com.dar.plugmid.interfaces.ActCallback
import com.dar.plugmid.interfaces.SerCallback
import kotlinx.android.synthetic.main.activity_plugin_main.*

/**
 * DarrenAdd20210126:插件页面1.
 *
 */
class PluginMainActivity : BaseActivity() {
    private val TAG:String = PluginMainActivity::class.java.name

    var act:ActCallback ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //dar这里要指定下接口类型，kt不知道为什么不会走子类实现，走的是超类实现。
        act = this
        act!!.setContentView(R.layout.activity_plugin_main)

        initLis()

        Toast.makeText(mainAct, "插件页面1", Toast.LENGTH_SHORT).show()

    }

    private val ACTION: String = "com.dar.pluginmodule.activitys.ACTION"
    private fun initLis() {
        //这里注意，不能直接findid，要用代理的对象。
        mainAct!!.bt_jumpplug.setOnClickListener{
            startActivity(Intent(mainAct, PluginSecActivity::class.java))
        }

        mainAct!!.bt_ser.setOnClickListener{
            startService(Intent(mainAct, PluginService::class.java))
        }

        mainAct!!.bt_send_broadcast.setOnClickListener{
            val intent = Intent()
            intent.action = ACTION
            sendBroadcast(intent)
        }

        mainAct!!.bt_reg_broadcast.setOnClickListener{
            val intentFilter = IntentFilter()
            intentFilter.addAction(ACTION)
            registerReceiver(PluginReceive(), intentFilter)
        }

    }
}
