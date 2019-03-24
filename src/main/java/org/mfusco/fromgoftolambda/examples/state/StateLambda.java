package org.mfusco.fromgoftolambda.examples.state;

import java.util.function.Supplier;

public class StateLambda {

	final static StateContext sc = new StateContext();
	static void display(Supplier<String> arg) {
		sc.writeName(arg.get());
	}

	public static void main(String[] args) {

		display(() -> "january");
		display(() -> "february");
		display(() -> "march");
		display(() -> "april");

		display(() -> "may");
		display(() -> "june");
		display(() -> "july");
		display(() -> "august");

		display(() -> "september");
		display(() -> "october");
		display(() -> "november");
		display(() -> "december");
	}

}
