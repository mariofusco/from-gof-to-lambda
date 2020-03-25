package org.mfusco.fromgoftolambda.examples.strategy;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class StrategyLambda {

    public static void publishText( String text,
                                    Predicate<String> filter,
                                    UnaryOperator<String> format) {
        if (filter.test( text )) {
            System.out.println( format.apply( text ) );
        }
    }

    public static void main( String[] args ) {
        publishText( "Plain text - I'm here", s -> true, s -> s );
        publishText( "ERROR - something bad happened", s -> true, String::toUpperCase );
        publishText( "DEBUG - I'm here", s -> s.length() < 20, String::toLowerCase );
    }
}
