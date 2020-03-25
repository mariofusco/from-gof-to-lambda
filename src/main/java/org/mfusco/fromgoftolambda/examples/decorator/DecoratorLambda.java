package org.mfusco.fromgoftolambda.examples.decorator;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.Stream;

public class DecoratorLambda {

//    public static class DefaultSalaryCalculator implements DoubleUnaryOperator {
//        @Override
//        public double applyAsDouble( double grossAnnual ) {
//            return grossAnnual / 12;
//        }
//    }

    public static void main(String[] args) {

        DoubleUnaryOperator defaultSalaryCalculator = grossAnnual -> grossAnnual / 12;

        defaultSalaryCalculator
                .andThen(Taxes::generalTax)
                .andThen(Taxes::regionalTax)
                .andThen(Taxes::healthInsurance)
                .applyAsDouble(80000.00);

        calculateSalary(80000.00, defaultSalaryCalculator, Taxes::generalTax, Taxes::regionalTax, Taxes::healthInsurance);
    }

    public static double calculateSalary(double annualGross, DoubleUnaryOperator... taxes) {
        DoubleUnaryOperator function = Stream.of(taxes).reduce(DoubleUnaryOperator.identity(), DoubleUnaryOperator::andThen);
        return function.applyAsDouble(annualGross);
    }
}
