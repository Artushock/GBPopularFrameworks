package com.artushock.home_work_2_2

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Function4
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
    Creation().exec()
    Operators().exec()
}

class Creation {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

        fun fromIterable(): @NonNull Observable<String> {
            return Observable.fromIterable(listOf("one", "two", "three"))
        }

        fun interval(): Observable<Long> = Observable.interval(1, TimeUnit.SECONDS)

        fun timer(): Observable<Long> = Observable.timer(3, TimeUnit.SECONDS)

        fun range(): Observable<Int> = Observable.range(1, 10)

        fun fromCallable(): Observable<Boolean> = Observable.fromCallable {
            randomResultOperation()
        }

        private fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun create(): Observable<String> = Observable.create { emitter ->
            try {
                for (i in 0..10) {
                    randomResultOperation().let {
                        if (it) {
                            emitter.onNext("Success $i")
                        } else {
                            emitter.onError(RuntimeException("Error"))
                            return@create
                        }
                    }
                }
                emitter.onComplete()
            } catch (t: Throwable) {
                emitter.onError(RuntimeException("Error"))
            }
        }
    }

    class Consumer(private val producer: Producer) {

        private val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(t: String) {
                println("onNext: $t")
            }

            override fun onError(e: Throwable) {
                println("onError: ${e.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        private fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        private fun execLambda() {
            val disposable = producer.just().subscribe(
                { string -> println("onNext: $string") },
                { error -> println("onError: $error") },
                { println("onComplete") }
            )
            disposable.dispose()
        }

        private fun execFromIterable() {
            producer.fromIterable().subscribe(stringObserver)
        }

        private fun execInterval() {
            producer.interval().subscribe {
                println("onNext:interval() $it")
            }
        }

        private fun execTimer() {
            producer.timer().subscribe {
                println("onNext:timer() $it")
            }
        }

        private fun execRange() {
            producer.range().subscribe {
                println("onNext:range() $it")
            }
        }

        private fun execFromCallable() {
            producer.fromCallable().subscribe {
                println("onNext:fromCallable() $it")
            }
        }

        private fun execCreate() {
            producer.create().subscribe {
                println("create(): $it")
            }
        }

        fun exec() {
            execJust()
            execFromIterable()
            execInterval()
            execTimer()
            execRange()
            execFromCallable()
            execCreate()

            Thread.sleep(5000)
            execLambda()
        }
    }
}

class Operators {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {
        fun createJust(): Observable<String> = Observable.just("1", "2", "3", "3")
        fun createJust2(): Observable<String> = Observable.just("4", "5", "6", "7")

    }

    class Consumer(private val producer: Producer) {

        private fun execTake() {
            producer.createJust()
                .take(3)
                .subscribe({ string ->
                    println("execTake() onNext: $string")
                }, { error ->
                    println("execTake() onError: ${error.message}")
                })
        }

        private fun execSkip() {
            producer.createJust()
                .skip(2)
                .subscribe({ string ->
                    println("execSkip() onNext: $string")
                }, { error ->
                    println("execSkip() onError: ${error.message}")
                })
        }

        private fun execMap() {
            producer.createJust()
                .map {
                    it + it
                }
                .subscribe({ string ->
                    println("createJust() onNext: $string")
                }, { error ->
                    println("createJust() onError: ${error.message}")
                })
        }

        private fun execDistinct() {
            producer.createJust()
                .distinct()
                .subscribe({ string ->
                    println("execDistinct() onNext: $string")
                }, { error ->
                    println("execDistinct() onError: ${error.message}")
                })
        }

        private fun execFilter() {
            producer.createJust()
                .filter {
                    it.toInt() > 1
                }
                .subscribe({ string ->
                    println("execFilter() onNext: $string")
                }, { error ->
                    println("execFilter() onError: ${error.message}")
                })
        }

        private fun execMerge() {
            producer.createJust()
                .mergeWith(producer.createJust2())
                .subscribe({ string ->
                    println("execMerge() onNext: $string")
                }, { error ->
                    println("execMerge() onError: ${error.message}")
                })
        }

        private fun execFlatMap() {
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@flatMap Observable.just(it + "x").delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe({ string ->
                    println("execFlaMap() onNext: $string")
                }, { error ->
                    println("execFlaMap() onError: ${error.message}")
                })
        }

        //switchMap() - отдает только последний результат.
        private fun execSwitchMap() {
            producer.createJust()
                .switchMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@switchMap Observable.just(it + "x").delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe({ string ->
                    println("execSwitchMap() onNext: $string")
                }, { error ->
                    println("execSwitchMap() onError: ${error.message}")
                })
        }

        private fun execZip() {
            val observable1 = Observable.just("1", "1_2").delay(1, TimeUnit.SECONDS)
            val observable2 = Observable.just("2", "2_2").delay(1, TimeUnit.SECONDS)
            val observable3 = Observable.just("3", "3_2").delay(1, TimeUnit.SECONDS)
            val observable4 = Observable.just("4", "4_2").delay(1, TimeUnit.SECONDS)

            Observable.zip(
                observable1,
                observable2,
                observable3,
                observable4,
                Function4<String, String, String, String, List<String>> { t1, t2, t3, t4 ->
                    return@Function4 listOf(t1, t2, t3, t4)
                }
            )
                .subscribeOn(Schedulers.computation())
                .subscribe({ list ->
                    println("execZip() onNext: $list")
                }, { error ->
                    println("execZip() onError: ${error.message}")
                })
        }

        fun exec() {
            execTake()
            execSkip()
            execMap()
            execDistinct()
            execFilter()
            execMerge()
            execZip()
            execFlatMap()
            execSwitchMap()

            Thread.sleep(10000)
        }

    }
}
