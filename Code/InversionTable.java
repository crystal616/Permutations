//*********************************
//* This class represents the inversion table of a permutation
//*********************************
public class InversionTable
{
	int[] inversionTable;

	public InversionTable(PermutationArray n)
	{
		inversionTable = new int[n.size];
		for (int i = 0; i < n.size; i++)
		{
			inversionTable[i] = -1;
		}

		for (int i = 0; i < n.size; i++)
		{
			int count = 0;
			int j = 0;
			while (inversionTable[i] == -1 && j < n.size)
			{
				if (n.permutation[j] > i + 1)
				{
					count++;	//find out how many numbers which are bigger than and on ahead of this number.
					j++;
				}

				else if (n.permutation[j] == i + 1)	//count until we "arrive" this number, stop and record
				{
					inversionTable[i] = count;
				} else
				{
					j++;
				}
			}
		}
	}

	public String toString()
	{
		String result = "";
		for (int i = 0; i < this.inversionTable.length; i++)
		{
			result += ("  " + this.inversionTable[i]);
		}
		return result;
	}
}
