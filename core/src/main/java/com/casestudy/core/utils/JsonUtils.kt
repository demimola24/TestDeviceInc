package com.casestudy.core.utils

import android.content.Context
import android.util.Log
import java.io.IOException





object JsonUtils {

    @JvmStatic
    fun getAllDevicesRaw(context: Context): String? {
        try {
            return FileUtil.readFileToString("mocked_user_list.json", context)
        } catch (e: IOException) {
            Log.d("JsonUtils", "getAllDevices error: $e")
        }
        return  ""
    }

}