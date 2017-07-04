package org.mfusco.fromgoftolambda.examples.command

fun log(message: String) = println("Logging: $message")
fun save(message: String) = println("Saving: $message")
fun send(message: String) = println("Sending: $message")

fun execute(tasks: List<() -> Unit>) = tasks.forEach { it() }

fun main(args: Array<String>) {
    val tasks = listOf({ log("Hi") }, { save("Cheers") }, { send("Bye") })

    execute(tasks)
}
