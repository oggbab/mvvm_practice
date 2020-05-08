package basic_test.util

import android.content.Context
import android.content.SharedPreferences

open class SharedPrefernceManager {

    lateinit var mPrefernceManager : SharedPreferences

    companion object {
        const val FILE_NAME = "pref_"
    }

    constructor(context : Context) {
        mPrefernceManager = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    fun writeString(key : String, value : String) {
        mPrefernceManager?.let {  it.edit().putString(key, value).commit() }
    }

    fun readString(key : String, value : String) {
        return mPrefernceManager?.let {  it.getString(key, value) }
    }

    fun clearPref() {
        mPrefernceManager.edit().clear().commit()
    }

}