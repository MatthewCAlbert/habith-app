package com.bncc.habith.ui.state

import androidx.lifecycle.LiveData

abstract class LiveDataStatus<T>() : LiveData<DataStatus<T>>() {
    protected open fun postLoading(data: T? = null) {
        postValue(DataStatus.loading(data))
    }

    protected open fun postError(throwable: Throwable) {
        postValue(DataStatus.error(throwable))
    }

    protected open fun postSuccess(data: T) {
        postValue(DataStatus.success(data))
    }

    protected open fun postEmpty() {
        postValue(DataStatus.empty())
    }
}