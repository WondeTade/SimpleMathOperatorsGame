package com.wonde.math;

import java.security.SecureRandom;

public class RandomNumberGenerator {

	
	public int[][] uniqueRandomNumberGenerator(int row, int level, int age) {
		
		SecureRandom rand = new SecureRandom();
		int randNumSize =  row * row * level; // Random number size
 
		//temporary number
		int temp = randNumSize;
		
		int[] tempRandNum = new int[randNumSize]; //temporary random number array
		int[] numList = new int[randNumSize];
		int[][] uniqueNumber = new int[row][row];
		
			
		
		if (age <= 10) 
		{
			age = 10;
			randNumSize = age;
			
			tempRandNum = new int[age]; //temporary random number array
			numList = new int[age];
			uniqueNumber = new int[(row + level)/2][(row + level)/2];
			
			for (int i = 1; i <= randNumSize; i++) 
			{
				tempRandNum[i-1] = i;
			}
			for (int i = 1; i <= randNumSize; i++) 
			{
				int j = rand.nextInt(age); //Generates a random index j from 0 to temp.
				numList[i-1] = tempRandNum[j]; //gets the value at index j and assigns to numList
				tempRandNum[j] = tempRandNum[age - 1];
				age --;
			}
			int k = 0;
			for (int i = 0; i < (row + level)/2; i++) 
			{
				for (int j = 0; j < uniqueNumber[i].length; j++) 
				{
					
					uniqueNumber[i][j] = numList[k];
					k++;
				}
			}
		}
		else if (age > 10 && age <= 16)
		{
			age = 10;
			randNumSize = age;
			
			tempRandNum = new int[age]; //temporary random number array
			numList = new int[age];
			uniqueNumber = new int[(row + level)/2][(row + level)/2];
			
			for (int i = 1; i <= randNumSize; i++) 
			{
				tempRandNum[i-1] = i;
			}
			for (int i = 1; i <= randNumSize; i++) 
			{
				int j = rand.nextInt(age); //Generates a random index j from 0 to temp.
				numList[i-1] = tempRandNum[j]; //gets the value at index j and assigns to numList
				tempRandNum[j] = tempRandNum[age - 1];
				age --;
			}
			int k = 0;
			for (int i = 0; i < (row + level)/2; i++) 
			{
				for (int j = 0; j < uniqueNumber[i].length; j++) 
				{
					
					uniqueNumber[i][j] = numList[k];
					k++;
				}
			}
		}
		else 
		{
			for (int i = 1; i <= randNumSize; i++) 
			{
				tempRandNum[i-1] = i;
			}
			for (int i = 1; i <= randNumSize; i++) 
			{
				int j = rand.nextInt(temp); //Generates a random index j from 0 to temp.
				numList[i-1] = tempRandNum[j]; //gets the value at index j and assigns to numList
				tempRandNum[j] = tempRandNum[temp - 1];
				temp --;
			}
			int k = 0;
			for (int i = 0; i < row; i++) 
			{
				for (int j = 0; j < uniqueNumber[i].length; j++) 
				{
					
					uniqueNumber[i][j] = numList[k];
					k++;
				}
			}
		}
		
		return uniqueNumber;
	}
	
}
