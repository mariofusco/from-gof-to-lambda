package org.mfusco.fromgoftolambda.examples.visitor

sealed class Figure
data class Square(val side: Double) : Figure()
data class Circle(val radius: Double) : Figure()
data class Rectangle(val width: Double, val height: Double) : Figure()

fun area(f: Figure) = when (f) {
    is Square -> f.side * f.side
    is Circle -> Math.PI * f.radius * f.radius
    is Rectangle -> f.height * f.width
}

fun perimeter(f: Figure) = when (f) {
    is Square -> 4 * f.side
    is Circle -> 2 * Math.PI * f.radius
    is Rectangle -> 2 * f.height + 2 * f.width
}

fun main(args: Array<String>) {
    val figures = listOf(Circle(4.0), Square(5.0), Rectangle(6.0, 7.0))

    val totalArea = figures.map { area(it) }.sum()
    println("Total area = $totalArea")

    val totalPerimeter = figures.map { perimeter(it) }.sum()
    println("Total perimeter = $totalPerimeter")
}
