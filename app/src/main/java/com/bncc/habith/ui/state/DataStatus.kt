package com.bncc.habith.ui.state

data class DataStatus<T>(val status: Status, val data: T? = null, val error: Throwable? = null) {

    enum class Status { LOADING, SUCCESS, ERROR, EMPTY }

    companion object {

        fun <T> success(data: T): DataStatus<T> {
            return DataStatus(Status.SUCCESS, data)
        }

        fun <T> loading(data: T?): DataStatus<T> {
            return DataStatus(Status.LOADING, data)
        }

        fun <T> error(error: Throwable): DataStatus<T> {
            return DataStatus(Status.ERROR, null, error)
        }

        fun <T> empty(): DataStatus<T> {
            return DataStatus(Status.EMPTY, null, null)
        }
    }

}