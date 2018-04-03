package com.clockworkant.boilerplate

import com.clockworkant.base.BasePresenter
import com.clockworkant.base.rxschedulers.AndroidSchedulerProvider
import com.clockworkant.base.rxschedulers.SchedulerProvider
import io.reactivex.Observable

class MainPresenter(
        private val view: View,
        private val schedulers: SchedulerProvider = AndroidSchedulerProvider
) : BasePresenter() {

    override fun subscribe() {
        addDisposible(
                view.onButtonClicked()
                        .subscribeOn(schedulers.ui())
                        .observeOn(schedulers.computation())
                        .flatMap {
                            //do work on background thread
                            println("Processing in the ${Thread.currentThread().name} thread")
                            return@flatMap Observable.just(it)
                        }
                        .observeOn(schedulers.ui())
                        .subscribe({
                            view.showButtonClicked()
                        }, {
                            view.showError(it)
                        })
        )
    }

    interface View {
        fun onButtonClicked(): Observable<Any>
        fun showButtonClicked()
        fun showError(error: Throwable)
    }
}