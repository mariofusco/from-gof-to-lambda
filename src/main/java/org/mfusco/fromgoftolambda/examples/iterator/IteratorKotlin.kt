package org.mfusco.fromgoftolambda.examples.iterator

val list = listOf(1, 2, 3, 4, 5, 6)

fun main(args: Array<String>) {
    list.forEach { println(it) }
}
