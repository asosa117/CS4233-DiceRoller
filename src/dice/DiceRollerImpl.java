/*
 * Implementation of a DiceRoller.
 *
 * Copyright © 2023, Gary F. Pollice
 */
package dice;

import java.util.Random;
import java.util.Scanner;

public class DiceRollerImpl implements DiceRoller {
	/**
	 * Only/default constructor
	 * @param numberOfSides
	 * @param numberOfDice
	 */
	private int numS;
	private int numD;
	private int[] DiceA;
	private int val = 0;
	public DiceRollerImpl(int numberOfSides, int numberOfDice) {
		numS = numberOfSides;
		numD = numberOfDice;
	}

	@Override
	public int roll(){
		if(numD >= 1 && numS >= 2){
			Random rand = new Random();
			DiceA = new int[numD];
			int currentDie = 0;
			for(int i = 0; i < numD; i++) {
				currentDie = rand.nextInt(numS) + 1;
				val += currentDie;
				DiceA[i] = currentDie;
			}
		}
		else {
			System.out.println("Number Of Dice Rolled Needs To Be Greater or Equal to 1 "
					+ "and Number Of Sides needs To Be Greater or equal to 2");
		}
		return val;
	}

	@Override
	public int getDiceTotal() {
		int total = -1;
		if(val != 0) {
			total = val;
		}
		return total;
	}

	@Override
	public int getDiceCount() {
		if(val == 0) {
			return -1;
		}
		return numD;
	}

	@Override
	public int getDieValue(int dieNumber) throws DiceException {
		int dVal = 0;
		if(val == 0) {
			throw new DiceException("Dice Have Not Been Rolled, Roll Dice First.");
		}
		if(dieNumber <= numD && dieNumber >= 1) {
			dVal = DiceA[dieNumber - 1];
		}
		else {
			throw new DiceException("Entered Number Is Not Valid");
		}

		return dVal;
	}

	public static void main(String args[]) throws DiceException
	{
		System.out.println("Enter the number of dice: ");
		Scanner nd = new Scanner(System.in);
		int n = nd.nextInt();
		System.out.println("Enter the number of sides: ");
		Scanner sd = new Scanner(System.in);
		int s = sd.nextInt();


		DiceRoller roller = new DiceRollerImpl(s, n);

		int rollnum = roller.roll();
		int NumOfDice = roller.getDiceCount();
		int DiceSum = roller.getDiceTotal();



		System.out.println("Number of Dice Rolled is: " + NumOfDice);
		System.out.println("Your Dice total is: " + DiceSum);

		while(true) {
			System.out.println("Enter which die's value you want to see: ");
			Scanner dv = new Scanner(System.in);
			int v = dv.nextInt();
			int DieVal = roller.getDieValue(v);
			System.out.println("Your Dice Value is: " + DieVal);
		}
	}

}
