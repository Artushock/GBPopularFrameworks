package com.artushock.geekbrainspopularframeworks

class MainPresenter(private val view: MainView) {

    private val model = CounterModel()

    fun counterClick(id: Int) {
        when (id) {
            0 -> {
                val nextValue = model.next(0)
                view.setFirstButtonText(nextValue.toString())
            }
            1 -> {
                val nextValue = model.next(1)
                view.setSecondButtonText(nextValue.toString())
            }
            2 -> {
                val nextValue = model.next(2)
                view.setThirdButtonText(nextValue.toString())
            }
        }
    }
}