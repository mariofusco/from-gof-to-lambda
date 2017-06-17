package org.mfusco.fromgoftolambda.examples.template

fun withResource(consumer: (Resource) -> Unit) {
    val r = Resource()

    try {
        consumer(r)
    } finally {
        r.dispose()
    }
}

fun main(args: Array<String>) {
    withResource { it.useResource() }
    withResource { it.employResource() }
}
