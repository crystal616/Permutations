//********************************
//* This class defines a permutation which is represented by an array.
//* A method which checks if user input is a real permutation.
//* A method re-write the permutation to make it starting from 1.
//********************************

import java.util.*;

public class PermutationArray implements FixedValues
{
	Random generator = new Random();

	int[] permutation, cyclic;
	int size;
	int currentIndex;
	boolean wrongInput = false;
	String warning = "";

	public PermutationArray()
	{
		currentIndex = 0;
		size = generator.nextInt(Max - Min + 1) + Min;
		permutation = new int[size];
		cyclic = new int[this.size];

		for (int i = 0; i < size; i++)
		{
			boolean neverUsed = false;
			int temp;
			do
			{
				temp = generator.nextInt(size) + 1;
				neverUsed = true;
				for (int j = 0; j < currentIndex; j++)
				{
					if (permutation[j] == temp)
					{
						neverUsed = false;
						continue;
					}
				}

			} while (neverUsed == false);
			permutation[currentIndex++] = temp;
		}
	}

	public PermutationArray(int s)
	{
		currentIndex = 0;
		size = s;
		permutation = new int[size];
		cyclic = new int[this.size];

		for (int i = 0; i < size; i++)
		{
			boolean neverUsed = false;
			int temp;
			do
			{
				temp = generator.nextInt(size) + 1;
				neverUsed = true;
				for (int j = 0; j < currentIndex; j++)
				{
					if (permutation[j] == temp)
					{
						neverUsed = false;
						continue;
					}
				}

			} while (neverUsed == false);
			permutation[currentIndex++] = temp;
		}
	}

	public PermutationArray(int[] input)
	{
		checkInput(input);
		if (!wrongInput)
		{
			this.currentIndex = 0;
			this.size = input.length;
			permutation = new int[size];
			cyclic = new int[this.size];

			for (int i = 0; i < size; i++)
			{
				this.permutation[i] = input[i];
				this.currentIndex++;
			}
		}
	}

	private void checkInput(int[] input)
	{
		int[] temp = new int[input.length];
		
		for (int i = 0; i < input.length; i++)
		{
			if (input[i] > input.length)
			{
				wrongInput = true;
				warning = "Number cannot be bigger than the size of permutation.";
				break;
			}

			if (temp[input[i]-1] != 0)
			{
				wrongInput = true;
				warning = "The number " + input[i] + " is repeated in the input.";
				break;
			} else
			{
				temp[input[i]-1] = input[i];
			}
		}
	}

	public String toString()
	{
		String result = "";
		for (int i = 0; i < this.size; i++)
		{
			result += (this.permutation[i] + "  ");
		}
		return result;
	}

	// re-write a cyclic permutation, making it starting from 1
	public void reArrangement()
	{
		int locationOfOne = -1;
		for (int i = 0; i < this.size; i++)
		{
			if (this.permutation[i] == 1)
			{
				locationOfOne = i;
				break;
			}
		}

		for (int i = locationOfOne; i < this.size; i++)
		{
			cyclic[i - locationOfOne] = this.permutation[i];
		}

		for (int i = 0; i < locationOfOne; i++)
		{
			cyclic[i + this.size - locationOfOne] = this.permutation[i];
		}
	}
}
