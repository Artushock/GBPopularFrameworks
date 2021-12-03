package com.artushock.geekbrainspopularframeworks

class Presenter (private val model: Model) {

    private var greetingView: GreetingView? = null

    fun attach(view: GreetingView){
        greetingView = view
    }

    fun onButtonClick(){
        greetingView?.let {
            val greeting = model.greeting
            it.setGreeting(greeting)
        }
    }

}