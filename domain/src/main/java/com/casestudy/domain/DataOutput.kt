package com.casestudy.domain

data class DataOutput<out T>(val status: Status, val data: T?, val message: String?, val error: String?,val statusCode: Int?) {

    companion object {

        fun <T> success(msg: String,data: T?): DataOutput<T> {
            return DataOutput(Status.SUCCESS, data, msg,null,0)
        }

        fun <T> error(errorMessage: String,statusCode: Int, data: T?): DataOutput<T> {
            return DataOutput(Status.ERROR, data, null,errorMessage,statusCode)
        }

        fun <T> loading(data: T? = null): DataOutput<T> {
            return DataOutput(Status.LOADING, data, null,null,0)
        }

    }

}