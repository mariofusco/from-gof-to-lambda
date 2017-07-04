package org.mfusco.fromgoftolambda.examples.interpreter

import java.util.Stack

val opMap = mapOf<String, (Int, Int) -> Int>(
        "+" to { a, b -> a + b },
        "*" to { a, b -> a * b },
        "-" to { a, b -> a - b }
)

fun evaluate(expression: String): Int {
    val stack = Stack<Int>()
    for (s in expression.split(" ")) {
        val op = opMap[s]
        if (op != null) {
            val right = stack.pop()
            val left = stack.pop()
            stack.push(op(left, right))
        } else {
            stack.push(Integer.parseInt(s))
        }
    }
    return stack.pop()
}

fun main(args: Array<String>) {
    val expression = "7 3 - 2 1 + *"
    println(evaluate(expression))
}
