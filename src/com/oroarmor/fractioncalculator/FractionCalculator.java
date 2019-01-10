package com.oroarmor.fractioncalculator;

import java.util.*;

public class FractionCalculator {

	public FractionCalculator() {

	}

	public Fraction evaluate(String equation) {
		int numOpenPara = 0;
		int numClosingPara = 0;
		int openLocation = -1;

		equation.replaceAll(" ", "");

		for (int i = equation.length() - 2; i > -1; i--) {
			if (equation.substring(i, i + 1).equals(" ")) {
				equation = equation.substring(0, i) + equation.substring(i + 1, equation.length());
			}
		}

		for (int i = 0; i < equation.length(); i++) {
			if (equation.substring(i, i + 1).equals("(")) {
				numOpenPara++;
				if (openLocation == -1) {
					openLocation = i;
				}
			}
			if (equation.substring(i, i + 1).equals(")")) {
				numClosingPara++;
				if (numClosingPara == numOpenPara && openLocation != -1) {
					Fraction para = evaluate(equation.substring(openLocation + 1, i));
					equation = equation.substring(0, openLocation) + para.toString()
							+ equation.substring(i + 1, equation.length());
					numClosingPara = 0;
					numOpenPara = 0;
				}
			}
		}
		System.out.println(equation);
		if (numClosingPara != numOpenPara) {
			throw (new IllegalArgumentException("Invaid Parentheses"));
		}

		String operators = "+-/*^_";

		ArrayList<Character> operations = new ArrayList<Character>();
		ArrayList<Fraction> fractions = new ArrayList<Fraction>();
		int i = 0;
		int pastIndex = 0;
		while (i < equation.length()) {
			if (operators.contains(equation.substring(i, i + 1))) {
				fractions.add(new Fraction(equation.substring(pastIndex, i), "1"));
				operations.add(equation.substring(i, i + 1).toCharArray()[0]);
				pastIndex = i + 1;
			}
			i++;
		}
		fractions.add(new Fraction(equation.substring(pastIndex), "1"));

		// fraction fixing
		for (i = 0; i < operations.size(); i++) {
			if (operations.get(i).toString().equals("_")) {
				Fraction wholeFraction = Fraction.divide(fractions.get(i), fractions.get(i + 1));
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}
		}
		// exponents
		for (i = 0; i < operations.size(); i++) {
			if (operations.get(i).toString().equals("^")) {
				Fraction wholeFraction = Fraction.power(fractions.get(i), fractions.get(i + 1));
				wholeFraction.printFraction();
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}
		}
		// mult and div
		for (i = 0; i < operations.size(); i++) {
			if (operations.get(i).toString().equals("*")) {
				Fraction wholeFraction = Fraction.multiply(fractions.get(i), fractions.get(i + 1));
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}
			if (i < 0) {
				i = 0;
			}
			if (operations.size() == 0) {
				break;
			}
			if (operations.get(i).toString().equals("/")) {
				Fraction wholeFraction = Fraction.divide(fractions.get(i), fractions.get(i + 1));
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}

		}
		// add and subtract
		for (i = 0; i < operations.size(); i++) {
			if (operations.get(i).toString().equals("+")) {
				Fraction wholeFraction = Fraction.add(fractions.get(i), fractions.get(i + 1));
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}
			if (i < 0) {
				i = 0;
			}
			if (operations.size() == 0) {
				break;
			}
			if (operations.get(i).toString().equals("-")) {
				Fraction wholeFraction = Fraction.subtract(fractions.get(i), fractions.get(i + 1));
				fractions.set(i, wholeFraction);
				fractions.remove(fractions.get(i + 1));
				operations.remove(i);
				i--;
			}
		}

		return fractions.get(0);
	}

}
