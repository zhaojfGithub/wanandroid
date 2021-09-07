package com.zhao.wanandroid.local

import android.content.Context
import com.zhao.wanandroid.MyApplication

/**
 *创建时间： 2021/9/6
 *编   写：  zjf
 *页面功能:
 */
private const val SP_SITE = "sp_wan_android"


fun <T> getSpValue(
    key: String,
    defaultVal: T,
): T {
    val sp = MyApplication.Instance().getSharedPreferences(SP_SITE, Context.MODE_PRIVATE)
    return when (defaultVal) {
        is Boolean -> sp.getBoolean(key, defaultVal) as T
        is String -> sp.getString(key, defaultVal) as T
        is Int -> sp.getInt(key, defaultVal) as T
        is Long -> sp.getLong(key, defaultVal) as T
        is Float -> sp.getFloat(key, defaultVal) as T
        is Set<*> -> sp.getStringSet(key, defaultVal as Set<String>) as T
        else -> throw IllegalArgumentException("Unrecognized default value $defaultVal")
    }
}


fun <T> putSpValue(
    key: String,
    value: T
) {
    val editor = MyApplication.Instance().getSharedPreferences(SP_SITE, Context.MODE_PRIVATE).edit()
    when (value) {
        is Boolean -> editor.putBoolean(key, value)
        is String -> editor.putString(key, value)
        is Int -> editor.putInt(key, value)
        is Long -> editor.putLong(key, value)
        is Float -> editor.putFloat(key, value)
        is Set<*> -> editor.putStringSet(key, value as Set<String>)
        else -> throw UnsupportedOperationException("Unrecognized value $value")
    }
    editor.apply()
}

@JvmOverloads
fun removeSpValue(
    filename: String = SP_SITE,
    context: Context = MyApplication.Instance(),
    key: String
) {
    context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        .edit()
        .remove(key)
        .apply()
}

@JvmOverloads
fun clearSpValue(filename: String = SP_SITE, context: Context = MyApplication.Instance()) {
    context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        .edit()
        .clear()
        .apply()
}
