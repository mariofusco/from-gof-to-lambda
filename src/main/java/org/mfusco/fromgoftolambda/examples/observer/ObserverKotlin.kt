package org.mfusco.fromgoftolambda.examples.observer

import kotlin.properties.Delegates

class Observable {
    private val listeners = mutableMapOf<Any, (Any) -> Unit>()

    fun register(key: Any, listener: (Any) -> Unit) = listeners.put(key, listener)

    fun unregister(key: Any) = listeners.remove(key)

    var event by Delegates.observable("") { _, _, event -> listeners.values.forEach { it(event) } }
}

fun main(args: Array<String>) {
    val observable = Observable()
    observable.register("key1") { println(it) }
    observable.register("key2") { println(it) }

    observable.event = "Hello World!"
}
