package org.mfusco.fromgoftolambda.examples.command;

import java.util.ArrayList;
import java.util.List;

public class CommandLambda {

    public static void log(String message) {
        System.out.println("Logging: " + message);
    }

    public static void save(String message) {
        System.out.println("Saving: " + message);
    }

    public static void send(String message) {
        System.out.println("Sending: " + message);
    }

    public static void execute(List<Runnable> tasks ) {
        tasks.forEach( Runnable::run );
        tasks.stream();
    }

    public static void main( String[] args ) {
        List<Runnable> tasks = new ArrayList<>();
        tasks.add(() -> log("Hi"));
        tasks.add(() -> save("Cheers"));
        tasks.add(() -> send("Bye"));

        execute( tasks );
    }
}
