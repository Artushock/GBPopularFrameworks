package com.artushock.geekbrainspopularframeworks

class MainPresenter(val view: MainView) {

    private val model = CounterModel()

    fun counterClick(id: Int) {
        when (id) {
            R.id.main_activity_button_1 -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.main_activity_button_2 -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.main_activity_button_3 -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }
}