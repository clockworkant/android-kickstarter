package com.clockworkant.boilerplate

import android.os.Bundle
import android.util.Log
import com.clockworkant.base.BaseActivityWithPresenter
import com.clockworkant.base.BasePresenter
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainPresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onButtonClicked(): Observable<Any> = RxView.clicks(main_activity_button)

    override fun showButtonClicked() {
        main_activity_textview.text = "The button has been clicked"
    }

    override fun showError(error: Throwable) {
        main_activity_textview.text = "There was an error ${error.message}"
        Log.e("MainActivityError", "error", error)
    }

    override fun createPresenter(): BasePresenter = MainPresenter(this)
}
