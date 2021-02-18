package com.dar.pluginmodule.activitys

import android.os.Bundle
import android.widget.Toast
import com.dar.pluginmodule.R
import com.dar.pluginmodule.base.BaseActivity
import com.dar.plugmid.interfaces.ActCallback

/**
 * DarrenAdd20210127：插件页面2。
 *
 */
class PluginSecActivity : BaseActivity() {

    private val TAG = PluginSecActivity::class.java.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var act = this as ActCallback
        act.setContentView(R.layout.activity_plugin_sec)

        initLis()

        Toast.makeText(mainAct, "插件页面2", Toast.LENGTH_SHORT).show()

        setContentView(R.layout.activity_plugin_sec)
    }

    private fun initLis() {

    }
}