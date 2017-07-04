package org.mfusco.fromgoftolambda.examples.strategy

open class TextFormatter(val filter: (String) -> Boolean, val format: (String) -> String)
class PlainTextFormatter : TextFormatter({ true }, { it })
class ErrorTextFormatter : TextFormatter({ it.startsWith("ERROR") }, { it.toUpperCase() })
class ShortTextFormatter : TextFormatter({ it.length < 20 }, { it.toLowerCase() })

fun publishText(text: String, textFormatter: TextFormatter) {
    if (textFormatter.filter(text)) println(textFormatter.format(text))
}

fun main(args: Array<String>) {
    publishText("INFO - all good!", PlainTextFormatter())
    publishText("ERROR - something bad happened", ErrorTextFormatter())
    publishText("DEBUG - I'm here", ShortTextFormatter())
}
