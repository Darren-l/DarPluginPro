package com.dar.testplug

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dar.testplug.manager.PlugMn
import com.dar.testplug.proxy.ProxyActivity
import com.dar.testplug.proxy.ProxyPlayer
import com.dar.testplug.proxy.ProxyReceive
import com.dar.testplug.proxy.ProxyService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        initOnLis()
    }


    private fun initOnLis() {
        bt_jumpplug.setOnClickListener {
            jumpPuginAct()
        }

        bt_load_plugin.setOnClickListener{
            initData()
        }

        bt_open_service.setOnClickListener{
            startSer()
        }

        bt_reg_broadcast.setOnClickListener{
            RegisterBro()
        }

        bt_send_broadcast.setOnClickListener{
            sendBro()
        }

        bt_module.setOnClickListener{
            testModule();
        }
    }

    /**
     * 测试动态注册广播。
     *
     */
    private val ACTION: String = "com.dar.testplug.ACTION"
    private fun RegisterBro() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION)
        registerReceiver(ProxyReceive(), intentFilter)
    }

    /**
     * 测试发送广播。
     *
     */
    private fun sendBro() {
        val intent = Intent()
        intent.action = ACTION
        sendBroadcast(intent)
    }

    /**
     * 测试站桩式对象策略。
     *
     */
    private fun testModule() {
        var proser = ProxyPlayer(this)
        proser.callPlugFun();
    }



    /**
     * 测试宿主开启插件中的服务。
     *
     */
    private fun startSer() {
        val intent = Intent(this, ProxyService::class.java)
        intent.putExtra("className", "com.dar.pluginmodule.services.PluginService")
        startService(intent)
    }

    /**
     * 测试宿主跳转插件的Act
     *
     */
    private fun jumpPuginAct() {
        //获取package信息，需要获取插件apk中act的类名。
        val activityInfo = PlugMn.parsingPackageInfo(
            this, PlugMn.plugApkPath!!
        )!!.activities[0]

        //dar意图给的是代理Act，跳转代理act，携带需要跳转的插件act类名，剩下交给代理act处理。
        val intent = Intent(this, ProxyActivity::class.java)
        intent.putExtra("className", activityInfo.name)
        startActivity(intent)
    }

    /**
     * 测试加载插件。
     *
     */

    private fun initData() {
        Log.d(TAG, "initData...");
        PlugMn.loadPlug(this)
    }

}