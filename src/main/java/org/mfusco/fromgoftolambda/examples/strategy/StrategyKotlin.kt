package org.mfusco.fromgoftolambda.examples.strategy

fun publishText(text: String, filter: (String) -> Boolean, format: (String) -> String) {
    if (filter(text)) println(format(text))
}

fun main(args: Array<String>) {
    publishText("ERROR - something bad happened", { _ -> true }, { it.toUpperCase() })
    publishText("DEBUG - I'm here", { it.length < 20 }, { it.toLowerCase() })
}