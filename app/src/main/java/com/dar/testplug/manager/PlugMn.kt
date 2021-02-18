package com.dar.testplug.manager

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.Resources
import android.util.Log
import dalvik.system.DexClassLoader
import java.io.File
import java.io.FileOutputStream

/**
 * Darren20210126: 插件管理类。
 *
 */
object PlugMn{

    private const val TAG: String="PlugMn"

    private var context:Context ?= null

    /**
     * 初始化上下文。
     *
     */
    var loadSus: Boolean = false
    fun loadPlug(context: Context){
        if(loadSus) return

        this.context = context
        loadSource(initData().absolutePath);
    }


    /**
     *  后面需要加载类创建对象都需要用这个加载器。
     */
    var dexClassLoader:DexClassLoader ?= null

    var resources:Resources ?= null


    /**
     * DarrenAdd20210126: 获取插件dex加载器，加载插件资源。
     *
     */
    private fun loadSource(plugPath: String) {

        try {
            /**
             * DarrenNote：
             * 1. 加载插件里面的 class。
             */

            // dexClassLoader的缓存目录：/data/data/当前应用的包名/pDir
            val fileDir = context!!.getDir("pDir", Context.MODE_PRIVATE)
            // 获取类的加载器
            dexClassLoader = DexClassLoader(plugPath, fileDir.absolutePath, null, context!!.classLoader)

            /**
             * 2. 加载resources。获取加载资源类，使用反射实现资源的加载,获取resources。
             *
             *  XXX::class.java (kt) == XXX.class (java)    -- 类获取class。
             *
             *  XXX::javaclass (kt) == XXX.getclass()   -- 对象获取class。
             *
             */
            val assetManager = AssetManager::class.java.newInstance()
            val addAssetPathMethod = assetManager.javaClass.getMethod(
                "addAssetPath", String::class.java
            )
            addAssetPathMethod.invoke(assetManager, plugPath)

            val re = context!!.resources;
            resources = Resources(assetManager, re.displayMetrics, re.configuration)

            loadSus = true
        }catch (e: Exception){
            Log.e(TAG,"loadSource error...");
        }

    }

    /**
     * 准备数据，拷贝apk包到缓存区。
     *
     */
    var plugApkPath: String? = null
    private fun initData() : File {
        val apk = File(context!!.cacheDir.toString() + "/pluginmodule-debug.apk")
        apk.delete()

        if (!apk.exists()) {
            try {
                val `is` = context!!.assets.open("apk/pluginmodule-debug.apk")
                val size = `is`.available()
                val buffer = ByteArray(size)
                `is`.read(buffer)
                `is`.close()
                val fos: FileOutputStream = FileOutputStream(apk)
                fos.write(buffer)
                fos.close()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        plugApkPath = apk.absolutePath
        return apk
    }


    /**
     * 获取包信息。
     *
     */
    fun parsingPackageInfo(context: Context, apkPath: String):PackageInfo?{
        val packageManager = context.packageManager
        return packageManager.getPackageArchiveInfo(plugApkPath!!, PackageManager.GET_ACTIVITIES)
    }

    /**
     * 根据类名获取一个对象。
     */
    fun getClassObj(className: String): Any?{
        var mPluginActivityClass = dexClassLoader!!.loadClass(className)
        val constructor = mPluginActivityClass!!.getConstructor(*arrayOf())
        val mPluginActivity = constructor.newInstance(*arrayOf())
        return mPluginActivity?:null
    }

}