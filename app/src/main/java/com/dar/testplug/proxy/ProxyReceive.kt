package com.dar.testplug.proxy

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dar.plugmid.interfaces.BroCallback
import com.dar.testplug.manager.PlugMn

class ProxyReceive: BroadcastReceiver(), BroCallback {
    override fun onReceive(context: Context?, intent: Intent?) {

        val plugRec = PlugMn.getClassObj("com.dar.pluginmodule.broadcast.PluginReceive")
        plugRec as BroCallback

        plugRec.onReceive(context,intent)

    }
}