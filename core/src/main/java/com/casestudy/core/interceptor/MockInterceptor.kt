package com.casestudy.core.interceptor

import android.content.Context
import com.casestudy.core.BuildConfig
import com.casestudy.core.utils.JsonUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull

/**
 * Created by Demimola on 1/29/22.
 */


class MockInterceptor(val context : Context) : Interceptor {
    /**
     * Assuming this is only used for debugging purposes
     */

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val responseString =  JsonUtils.getAllDevicesRaw(context) ?:""

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        "application/json".toMediaTypeOrNull(),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes")
        }
    }

}