package com.artushock.geekbrainspopularframeworks

import moxy.MvpPresenter

class MainPresenter(private val model: CounterModel): MvpPresenter<MainView>() {

    fun counterClick(id: Int) {
        when (id) {
            0 -> {
                val nextValue = model.next(0)
                viewState.setFirstButtonText(nextValue.toString())
            }
            1 -> {
                val nextValue = model.next(1)
                viewState.setSecondButtonText(nextValue.toString())
            }
            2 -> {
                val nextValue = model.next(2)
                viewState.setThirdButtonText(nextValue.toString())
            }
        }
    }
}