package com.artushock.home_work_4

import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.random.Random

fun main() {
    //Sources().exec()
    BackPressure().exec()
}

class Sources {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer {

        fun completable(): Completable = Completable.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        fun single(): Single<String> = Single.fromCallable {
            return@fromCallable "Some string value"
        }

        fun maybe(): Maybe<String> = Maybe.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("Success: $it")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }

        fun hotObservable(): Observable<Long> =
            Observable.interval(1, TimeUnit.SECONDS)
                //.publish()
                //.replay()
                //.refCount()
                .cache()

        fun publishSubject(): PublishSubject<String> = PublishSubject.create<String>().apply {
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    onNext("Value from subject")
                }
        }

        fun test() {
            Observable.just(0)
                .onErrorResumeNext {
                    return@onErrorResumeNext Observable.just(0)
                }
                .retry(5)
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable) {
                        TODO("Not yet implemented")
                    }

                    override fun onNext(t: Int) {
                        TODO("Not yet implemented")
                    }

                    override fun onError(e: Throwable) {
                        TODO("Not yet implemented")
                    }

                    override fun onComplete() {
                        TODO("Not yet implemented")
                    }
                })
        }

        private fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }


    }

    class Consumer(private val producer: Producer) {

        fun exec() {
//            execCompletable()
//            execSingle()
//            execMaybe()
//            execHotObservable()
            execPublisherSubject()

            Thread.sleep(5000)
        }

        private fun execPublisherSubject() {
            val subject = producer.publishSubject()
            subject
                .subscribe(
                    { println("onNext $it") },
                    { println("onError ${it.message}") })

            subject.onNext("from exec")
        }

        private fun execCompletable() {
            producer.completable().subscribe(
                { println("onComplete") },
                { println("onError: ${it.message}") }
            )
        }

        private fun execSingle() {
            producer.single().subscribe(
                { println("OnSuccess: $it") },
                { println("OnError: ${it.message}") })
        }

        private fun execMaybe() {
            producer.maybe()
                .map { it + it }
                .subscribe(
                    { println("onSuccess: $it") },
                    { println("onError: ${it.message}") },
                    { println("onComplete") })
        }

        private fun execHotObservable() {
            val hotObservable = producer.hotObservable()

            hotObservable.subscribe {
                println(it)
            }

            //hotObservable.connect()
            //instruct a connectable Observable to begin emitting items to its subscribers

            Thread.sleep(3000)

            hotObservable.subscribe {
                println("delayed: $it")
            }
        }
    }
}

class BackPressure {
    fun exec() {
        val producer = Producer()
        val consumer = Consumer(producer)
        consumer.consume()
        Thread.sleep(10000)
    }

    class Producer {

        fun noBackPressure(): Observable<Int> =
            Observable.range(0, 10000000).subscribeOn(Schedulers.io())

        fun backPressure(): Flowable<Int> =
            Flowable.range(0, 10000000).subscribeOn(Schedulers.io()).onBackpressureLatest()
    }

    class Consumer(private val producer: Producer) {

        fun consume() {
            //consumeNoBackPressure()
            consumeBackPressure()
        }

        private fun consumeNoBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation())
                .subscribe({
                    Thread.sleep(1000)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }

        private fun consumeBackPressure() {
            producer.backPressure()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe({
                    Thread.sleep(300)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}