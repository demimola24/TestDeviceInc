package com.casestudy.core.utils

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset


object FileUtil {
    @JvmStatic
    @Throws(IOException::class)
    fun readFileToString(filename: String, context: Context): String {
        val manager = context.assets
        val file = manager.open(filename)
        val buffer = ByteArray(file.available())
        file.read(buffer)
        file.close()
        return String(buffer, Charset.forName("UTF-8"))
    }


}