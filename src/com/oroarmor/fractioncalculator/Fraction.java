package com.oroarmor.fractioncalculator;

import java.math.BigInteger;

public class Fraction {

	BigInteger Nuemerator;
	BigInteger Denomenator;

	public Fraction(String _Nuemerator, String _Denomenator) {
		// TODO Auto-generated constructor stub
		Nuemerator = new BigInteger(_Nuemerator);
		Denomenator = new BigInteger(_Denomenator);
	}

	public Fraction(BigInteger _Nuemerator, BigInteger _Denomenator) {
		Nuemerator = _Nuemerator;
		Denomenator = _Denomenator;
	}

	public void printFraction() {
		System.out.print(Nuemerator.toString(10));
		if (!Denomenator.toString(10).equals("1")) {
			System.out.print("/");
			System.out.print(Denomenator.toString(10));
		}
		System.out.println();
	}

	public static Fraction multiply(Fraction one, Fraction two) {

		BigInteger newNuemerator = one.Nuemerator.multiply(two.Nuemerator);
		BigInteger newDenomenator = one.Denomenator.multiply(two.Denomenator);

		Fraction product = new Fraction(newNuemerator, newDenomenator);
		product.simplify();

		return product;
	}

	public void multiply(int num) {
		Nuemerator.multiply(new BigInteger(num+""));
	}
	
	public static Fraction divide(Fraction one, Fraction two) {

		return multiply(one, inverse(two));
	}

	public static Fraction inverse(Fraction fraction) {
		return new Fraction(fraction.Denomenator, fraction.Nuemerator);
	}
	

	private void simplify() {
		BigInteger gcf = Nuemerator.gcd(Denomenator);
		Nuemerator = Nuemerator.divide(gcf);
		Denomenator = Denomenator.divide(gcf);
	}

	public static Fraction add(Fraction one, Fraction two) {

		BigInteger GCF = one.Denomenator.gcd(two.Denomenator);

		BigInteger twoMultiplier = one.Denomenator.divide(GCF);
		Fraction newTwo = new Fraction(two.Nuemerator.multiply(twoMultiplier), two.Denomenator.multiply(twoMultiplier));

		BigInteger oneMultiplier = two.Denomenator.divide(GCF);
		Fraction newOne = new Fraction(one.Nuemerator.multiply(oneMultiplier), one.Denomenator.multiply(oneMultiplier));

		BigInteger sumNuemerator = newOne.Nuemerator.add(newTwo.Nuemerator);
		BigInteger sumDenomenator = newOne.Denomenator;

		Fraction sum = new Fraction(sumNuemerator, sumDenomenator);
		sum.simplify();

		return sum;
	}
	
	public static Fraction subtract(Fraction one, Fraction two) {
		return add(one,multiply(two,new Fraction("-1","1")));
	}

}
