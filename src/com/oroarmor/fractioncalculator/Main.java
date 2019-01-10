package com.oroarmor.fractioncalculator;

public class Main {

	public static void main(String[] args) {

		FractionCalculator test = new FractionCalculator();
		test.evaluate("1_2 + (1_2*4) ^2").printFraction();
	}

}
