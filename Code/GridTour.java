//********************************
//* This class defines a grid tour and all needed methods.
//********************************
public class GridTour
{
	int size, currentIndex;
	int[] row, column;
	int[] permutation, reWriteColumn;
	GridTourPath[] paths;
	boolean gridTour;
	String warning = "";

	public GridTour(PermutationArray n)
	{
		size = n.size;

		//re-write the cyclic permutation, making it starting from 1
		n.reArrangement();

		row = n.cyclic;
		this.permutation = n.permutation;

		//check if this permutation could satisfy the requirement of a grid tour
		gridTour = gridTour(this.row);

		if (gridTour)
		{
			//generate a column which can work with this permutation
			column = generateColumn(this.size);
			paths = generatePaths();
			//re-write the column, making it works with the original row permutation, not the re-wrote one
			reWriteColumn = reWriteColumn();
		}
	}

	private int[] reWriteColumn()
	{
		int[] temp = new int[this.size];
		int locationOfOne = -1;
		for (int i = 0; i < this.size; i++)
		{
			if (this.permutation[i] == 1)	//find out the original place of number 1 in row permutation
			{
				locationOfOne = i;
			}
		}
		for (int i = locationOfOne; i < this.size; i++)
		{
			temp[i] = this.column[i - locationOfOne];
		}

		for (int i = 0; i < locationOfOne; i++)
		{
			temp[i] = this.column[i + size - locationOfOne];
		}

		return temp;
	}

	private GridTourPath[] generatePaths()
	{
		//the number of paths is double of the number of numbers in the permutation
		GridTourPath[] temp = new GridTourPath[this.size * 2];
		for (int i = 0; i < this.size - 1; i++)		//a path is from row[i]column[i] to row[i]column[i+1]
		{											//another path from row[i]column[i+1] to row[i+1]column[i+1]
			temp[i * 2] = new GridTourPath(row[i], column[i], row[i], column[i + 1]);
			temp[i * 2 + 1] = new GridTourPath(row[i], column[i + 1], row[i + 1], column[i + 1]);
		}

		int current = this.size - 1;
		temp[current * 2] = new GridTourPath(row[current], column[current], row[current], column[0]);
		temp[current * 2 + 1] = new GridTourPath(row[current], column[0], row[0], column[0]);
		return temp;
	} 

	private int[] generateColumn(int size2)
	{
		int[] temp = (new PermutationArray(size2)).permutation;
		while (!gridTour(temp))		//keep generating permutation until we find one which satisfy the requirement of
		{							//a grid tour
			temp = (new PermutationArray(size2)).permutation;
		}
		return temp;
	}

	public boolean gridTour(int[] row)
	{
		if (this.size % 2 == 1)
		{
			warning = "The number of numbers in the permutation must be even!";
			return false;
		}
		for (int i = 0; i < this.size - 1; i++)
		{
			if (i % 2 == 0)		//the number which is on an "even" place should be smaller than the next number
			{
				if (row[i] > row[i + 1])
				{
					warning = "At least one left-turn must be taken!";
					return false;
				}
			} else
			{
				if (row[i] < row[i + 1])	//the number which is on an "odd" place should be greater than the next number
				{
					warning = "At least one left-turn must be taken!";
					return false;
				}
			}
		}

		if (row[0] > row[this.size - 1])	//the first number should be smaller than the last one
		{
			return false;
		}

		return true;
	}

	public String rowToString()
	{
		String row = "(";
		for (int i = 0; i < this.size; i++)
		{
			row += " " + this.permutation[i];
		}

		row += " )";
		return "row:" + row;
	}

	public String columnToString()
	{
		String column = "(";
		for (int i = 0; i < this.size; i++)
		{
			column += " " + this.reWriteColumn[i];
		}

		column += " )";
		return "  column:" + column;
	}

}
