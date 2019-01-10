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
		int openLocation = -1;
		for(int i = 0; i < equation.length(); i++) {
			if(equation.substring(i,i+1).equals("(")) {
				numOpenPara++;
				if(openLocation == -1) {
					openLocation = i;
				}
			}
			if(equation.substring(i,i+1).equals(")")) {
				numClosingPara++;
				if(numClosingPara == numOpenPara && openLocation != -1) {
					Fraction para = evaluate(equation.substring(openLocation+1, i));
					equation = equation.substring(0, openLocation)+para.toString()+equation.substring(i, equation.length()-1);
					numClosingPara=0;
					numOpenPara = 0;
				}
			}
		}
		System.out.println(equation);
		if(numClosingPara != numOpenPara) {
			throw( new IllegalArgumentException("Invaid Parentheses"));
		}
		return new Fraction("1","2");
	}

}
