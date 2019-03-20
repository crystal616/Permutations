//*************************************
//* This class defines the cyclic notation of a permutation.
//* A helper class sequence which is an integer array.
//* Cyclic notation is represented by an array of sequence objects.
//*************************************
public class CyclicNotation 
{
	sequence[] cyclicNotation;
	int current;
	int count;
	int[] copy;

	public CyclicNotation(PermutationArray n)
	{
		cyclicNotation = new sequence[n.size];
		copy = new int[n.size];

		for (int i = 0; i < n.size; i++)
		{
			copy[i] = n.permutation[i];
			cyclicNotation[i] = new sequence(n.size);	//the numbers that each sequence needs to hold would not 
		}												//more than the number of numbers in the permutation

		count = 0;

		for (int i = 0; i < n.size; i++)
		{
			if (copy[i] == i + 1)					//the integers that stay in their "correct" places are
			{										//kept by the corresponding entry in the array. 
				this.cyclicNotation[i].add(i + 1);	
				copy[i] = -1;						//mark those "used" number
				count++;
			}
		}

		while (count < n.size)
		{
			int temp, start;
			for (int i = 0; i < n.size; i++)	//check each number of the permutation from the first number
			{
				if (copy[i] != -1)		//if this number is "not used"
				{
					this.cyclicNotation[i].add(i + 1);	//add this number to its corresponding sequence object, this will
					start = i + 1;						//be the smallest number in this "cycle"
					temp = i;
					while (copy[temp] != -1 && count < n.size)
					{
						int index = temp;
						temp = copy[temp];
						copy[index] = -1;
						count++;

						if (temp == start)
						{
							break;	//when this "cycle" is finished
						} else
						{
							this.cyclicNotation[i].add(temp);	//add number to the sequence object which corresponds to
							temp--;								//the smallest number of this "cycle"
							continue;
						}
					}
				}
			}
		}
	}

	public String toString()
	{
		String result = "";

		for (int j = 0; j < this.cyclicNotation.length; j++)
		{
			if (this.cyclicNotation[j].currentIndex > 1)
			{
				result += this.cyclicNotation[j].toString() + " ";
			}
		}

		for (int j = 0; j < this.cyclicNotation.length; j++)
		{
			if (this.cyclicNotation[j].currentIndex == 1)
			{
				result += this.cyclicNotation[j].toString() + " ";
			}
		}
		return result;
	}

	private class sequence
	{
		int currentIndex;
		int[] sequence;

		public sequence(int i)
		{
			currentIndex = 0;
			sequence = new int[i];
		}

		public void add(int n)
		{
			sequence[currentIndex++] = n;
		}

		public String toString()
		{
			String result = "(";
			for (int i = 0; i < currentIndex - 1; i++)
			{
				result += (sequence[i] + "  ");
			}
			result += (sequence[this.currentIndex - 1] + ")");
			return result;
		}

	}
}
