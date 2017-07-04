package org.mfusco.fromgoftolambda.examples.decorator

infix fun <P1, IP, R> ((P1) -> IP).andThen(f: (IP) -> R): (P1) -> R = { p1: P1 -> f(this(p1)) } // from funKTionale library

fun defaultSalaryCalculator(grossAnnual: Double): Double = grossAnnual / 12

fun main(args: Array<String>) {
    println((::defaultSalaryCalculator andThen Taxes::generalTax andThen Taxes::regionalTax andThen Taxes::healthInsurance)(80000.00))

    println(calculateSalary(80000.00, ::defaultSalaryCalculator, Taxes::generalTax, Taxes::regionalTax, Taxes::healthInsurance))
}

fun calculateSalary(annualGross: Double, vararg taxes: (Double) -> Double) = listOf(*taxes).fold(annualGross) { acc, tax -> tax(acc) }
