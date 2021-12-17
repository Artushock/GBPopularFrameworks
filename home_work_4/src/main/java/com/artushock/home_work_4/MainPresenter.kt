package com.artushock.home_work_4

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.ReplaySubject
import moxy.MvpPresenter
import kotlin.math.pow
import kotlin.math.sqrt

class MainPresenter : MvpPresenter<MainView>() {

    private val subject: ReplaySubject<Double> = ReplaySubject.create()

    private val observerSquareResult: Observable<Double> = getSquareObservable(subject)
    private val observerSquareRootResult: Observable<Double> = getSquareRootObservable(subject)
    private val observerCubeResult: Observable<Double> = getCubeObservable(subject)
    private val observerCubeRootResult: Observable<Double> = getCubeRootObservable(subject)

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setSquareResult(0.0)
        viewState.setSquareRootResult(0.0)
        viewState.setCubeResult(0.0)
        viewState.setCubeRootResult(0.0)
    }

    fun subscribe() {
        observerSquareResult
            .subscribe {
                viewState.setSquareResult(it)
            }

        observerSquareRootResult
            .subscribe {
                viewState.setSquareRootResult(it)
            }

        observerCubeResult
            .subscribe {
                viewState.setCubeResult(it)
            }

        observerCubeRootResult
            .subscribe {
                viewState.setCubeRootResult(it)
            }

    }

    fun calculate(number: Double) {
        subject.onNext(number)
    }

    private fun getSquareObservable(subject: ReplaySubject<Double>) =
        subject
            .map {
                it.pow(2)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getSquareRootObservable(subject: ReplaySubject<Double>) =
        subject
            .map {
                sqrt(it)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getCubeObservable(subject: ReplaySubject<Double>) =
        subject
            .map {
                it.pow(3)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())

    private fun getCubeRootObservable(subject: ReplaySubject<Double>) =
        subject
            .map {
                Math.cbrt(it)
            }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
}