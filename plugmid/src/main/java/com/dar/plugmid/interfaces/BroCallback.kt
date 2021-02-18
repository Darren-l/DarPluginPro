package com.dar.plugmid.interfaces

import android.content.Context
import android.content.Intent

/**
 * DarrenAdd20210128：广播抽象类。
 *
 */
interface BroCallback {

    fun onReceive(context: Context?, intent: Intent?)

}