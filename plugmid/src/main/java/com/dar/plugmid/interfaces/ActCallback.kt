package com.dar.plugmid.interfaces

import android.app.Activity
import android.os.Bundle

/**
 * DarrenAdd20210126:
 *
 *  抽象ACT接口，增加宿主与插件的关联接口。
 *
 *
 */
interface ActCallback {

    //Act生命周期
    fun onStart()
    fun onResume()
    fun onDestroy()
    fun onCreate(savedInstanceState: Bundle?)
    fun setContentView(resId: Int?)
    fun findViewById(layoutId: Int?)

    fun insertAppContext(appActivity: Activity)
}