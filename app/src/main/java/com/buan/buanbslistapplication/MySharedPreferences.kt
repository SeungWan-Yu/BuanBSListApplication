package com.buan.buanbslistapplication

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences(context: Context)  {
    val PREFS_FILENAME = "prefs"
    val PREF_KEY_MY_ID = "myEditText"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0)
    var checkvalue = 0

    var check: Int
        get() = prefs.getInt(PREF_KEY_MY_ID, 0)
        set(value) = prefs.edit().putInt(PREF_KEY_MY_ID, value).apply()

    var first: Int
        get() = prefs.getInt("first", 0)
        set(value) = prefs.edit().putInt("first", value).apply()
}
