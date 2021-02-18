package com.dar.pluginmodule.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import com.dar.plugmid.interfaces.ActCallback

open class BaseActivity : ActCallback,Activity() {

    private val TAG: String = BaseActivity::class.java.name;

    var mainAct: Activity ?= null
    override fun insertAppContext(mainAct: Activity) {
        this.mainAct = mainAct
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {

    }

    @SuppressLint("MissingSuperCall")
    override fun findViewById(layoutId: Int?){
        mainAct!!.findViewById<View>(layoutId!!)
    }

    @SuppressLint("MissingSuperCall")
    override fun setContentView(resId: Int?){
        mainAct!!.setContentView(resId!!)
    }

    @SuppressLint("MissingSuperCall")
    override fun onStart() {
    }

    @SuppressLint("MissingSuperCall")
    override fun onResume() {
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
    }

    override fun startActivity(intent: Intent) {
        val intentNew = Intent()
        intentNew.putExtra("className", intent.component!!.className)
        mainAct!!.startActivity(intentNew)
    }


    override fun startService(service: Intent?): ComponentName? {
        val intentNew = Intent()
        intentNew.putExtra("className", service!!.component!!.className)
        return mainAct!!.startService(intentNew)
    }

    override fun sendBroadcast(intent: Intent?) {
        mainAct!!.sendBroadcast(intent)
    }

    override fun registerReceiver(receiver: BroadcastReceiver?, filter: IntentFilter?): Intent? {
        return mainAct!!.registerReceiver(receiver,filter)
    }
}