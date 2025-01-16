package com.tus.random;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.codec.binary.Hex;

public class Randomish {

	public static void main(String[] args) {
		threeRandNumLin();

	}

	public static void threeRandNumMath() {
		double n1 = Math.random();
		double n2 = Math.random();
		double n3 = Math.random();
		System.out.println("number 1: " + n1);
		System.out.println("number 2: " + n2);
		System.out.println("number 3: " + n3);
	}

	public static void threeRandNumUtil() {
		Random rand = new Random(1);
		double n1 = rand.nextDouble();
		double n2 = rand.nextDouble();
		double n3 = rand.nextDouble();
		System.out.println("number 1: " + n1);
		System.out.println("number 2: " + n2);
		System.out.println("number 3: " + n3);
	}

	public static void threeRandNumUtil2() {
		Random rand = new Random();
		int n1 = rand.nextInt();
		double n2 = rand.nextDouble();
		int n3 = rand.nextInt(100);
		System.out.println("number 1: " + n1);
		System.out.println("number 2: " + n2);
		System.out.println("number 3: " + n3);
	}

	public static void threeRandNumSec() {
		SecureRandom rand = new SecureRandom();
		byte[] seed = rand.generateSeed(20);
		rand.setSeed(seed);
		int n1 = rand.nextInt(1000);
		String hexC = Hex.encodeHexString(seed);

		System.out.println("number 1: " + n1);
		System.out.println("seed: " + seed);
		System.out.println("encoded: " + hexC);
	}

	public static void threeRandNumLin() {
		int seed = 5;
		int mod = 7;
		int multiplier = 3;
		int inc = 3;
		int prev = seed;

		for (int i = 0; i < 20; i++) {
			int random = ((prev * multiplier) + inc) % mod;
			prev = random;
			System.out.println(random + " ");
		}

	}

}
