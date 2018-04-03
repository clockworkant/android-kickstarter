package com.clockworkant.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    abstract fun subscribe()

    fun unsubscribe() {
        compositeDisposable.clear()
    }

    fun addDisposible(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}