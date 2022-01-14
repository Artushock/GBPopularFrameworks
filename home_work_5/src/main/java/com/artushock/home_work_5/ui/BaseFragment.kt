package com.artushock.home_work_5.ui

import android.view.View
import moxy.MvpAppCompatFragment

open class BaseFragment: MvpAppCompatFragment() {

    protected fun changeViewVisibility(view: View, tumbler: Boolean) {
        view.visibility = if (tumbler) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

}