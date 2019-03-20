//************************************
//* This class defines the method that are used to visualize a grid tour
//************************************
public class GridTourCanvas implements FixedValues
{
	GridTourPath[] paths;
	GridTour tour;
	int[] permutation;
	int startX, startY;		//coordinates of most left upper corner of the square
	String title = "Grid Tour: ";
	int titleX, titleY;
	int lineDistance, size;
	final int totalLength = HalfPartY * 2 - Margin * 12;	//size of the big square

	public GridTourCanvas(GridTour t, int partX, int partY)
	{
		this.tour = t;
		size = t.size;
		this.permutation = new int[size];
		title += t.rowToString();

		for (int i = 0; i < size; i++)
		{
			this.permutation[i] = i + 1;
		}

		paths = t.paths;

		startX = partX + Margin * 15;
		startY = partY + Margin * 6 + 20;

		if (partX == Part2X && partY == Part2Y)
		{
			startX = partX + Margin * 24;
		}

		if (partX == Part4X && partY == Part4Y)
		{
			startX = partX + Margin * 24;
			titleX = partX + Part5Length / 2 + 20;
			titleY = partY + 30;
		} else
		{
			titleX = partX + 20;
			titleY = partY + 30;
		}

		lineDistance = totalLength / (size - 1);	

		if (!t.gridTour)	//if this permutation cannot form a grid tour
		{
			return;
		}

		for (int i = 0; i < this.paths.length; i++)
		{
			paths[i].setLocations(startX, startY, lineDistance);
		}
	}

}
