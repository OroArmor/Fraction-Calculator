package com.oroarmor.fractioncalculator;

import java.util.*;

public class FractionCalculator {

	Fraction[] fractions;
	String[] operations;
	
	public FractionCalculator() {
		
	}
	
	public Fraction evaluate(String equation) {
		int numOpenPara = 0;
		int numClosingPara = 0;
		ArrayList<Integer> openLocation = new ArrayList<Integer>();
		int[] closingLocation = new int[equation.length()];
		for(int i = 0; i < equation.length(); i++) {
			if(equation.substring(i,i+1).equals("(")) {
				numOpenPara++;
				openLocation.add(i);
			}
		}
		return null;
	}

}
