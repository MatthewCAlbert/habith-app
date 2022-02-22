package com.bncc.habith.ui.state

class MutableLiveDataStatus<T> : LiveDataStatus<T>() {
    public override fun postLoading(data: T?) {
        super.postLoading(data)
    }

    public override fun postError(throwable: Throwable) {
        super.postError(throwable)
    }

    public override fun postSuccess(data: T) {
        super.postSuccess(data)
    }

    public override fun postEmpty() {
        super.postEmpty()
    }
}