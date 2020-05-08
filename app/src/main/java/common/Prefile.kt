package common

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils

open class Prefile {

    private lateinit var mFileName : String
    private lateinit var mPreferences: SharedPreferences

    constructor(context: Context) {
        mFileName = context.packageName + "_preference"
        mPreferences = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE)
    }

    private fun write(key: String, value: String) {
        mPreferences.edit().putString(key, value).commit()
    }

    private fun read(key: String, default: String) : String? {
        return mPreferences.getString(key, default)
    }
}
