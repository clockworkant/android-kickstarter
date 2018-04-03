package com.clockworkant.base

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragmentWithPresenter : Fragment() {
    lateinit var presenter: BasePresenter

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    abstract fun createPresenter(): BasePresenter
}