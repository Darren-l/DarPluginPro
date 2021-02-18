package com.dar.pluginmodule.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.dar.plugmid.interfaces.BroCallback

class PluginReceive: BroadcastReceiver(),BroCallback {

    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context!!,"### 插件收到广播了！",Toast.LENGTH_LONG).show()
    }
}