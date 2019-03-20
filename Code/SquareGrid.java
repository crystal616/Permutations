//***************************************
//* Defines a square grid.
//***************************************
public class SquareGrid
{
	int[] permutation;
	int[][] squareGrid;

	public SquareGrid(PermutationArray p)
	{
		this.permutation = p.permutation;

		squareGrid = new int[p.size][p.size];	//represented by a 2-D array, initial value is 0 by default

		for (int i = 0; i < p.size; i++)
		{
			int column = this.permutation[i];
			squareGrid[i][column - 1] = this.permutation[i];	//write the number in permutation in its "right" place(entry), making it "colored"
			for (int j = i + 1; j < p.size; j++)	//replace all entries which are below this entry with -1.
			{
				squareGrid[j][column - 1] = -1;		//a -1 indicates an empty small square in grid
			}
		}

		for (int i = 0; i < p.size; i++)
		{
			int column = 0;
			boolean meet = false;
			while (column < p.size)
			{
				if (squareGrid[i][column] > 0)
				{
					meet = true;
				}

				if (squareGrid[i][column] == 0 && meet)
				{
					for (int j = column; j < p.size; j++)
					{
						squareGrid[i][j] = -1;	//make all small squares "empty" which are in the same row but after the "colored" entry
					}
				}
				column++;
			}
		}
	}
	
}
