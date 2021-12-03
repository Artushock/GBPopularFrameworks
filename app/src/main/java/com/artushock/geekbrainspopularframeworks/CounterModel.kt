package com.artushock.geekbrainspopularframeworks

class CounterModel {

    private val counters = mutableListOf(0, 0, 0)

    fun getCurrent(index: Int): Int = counters[index]

    fun next(index: Int): Int {
        counters[index]++
        return getCurrent(index)
    }

}