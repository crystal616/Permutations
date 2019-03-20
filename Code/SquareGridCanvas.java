//*****************************************
//* A class used for visualization of a square grid.
//*****************************************
public class SquareGridCanvas implements FixedValues
{
	int[][] table;
	int startX, startY, endX, endY;
	int squareSize;
	String title;
	int titleX, titleY;
	int tableSize;

	public SquareGridCanvas(SquareGrid n, int partX, int partY)
	{
		title = "Square Grid of this permutation:";
		table = n.squareGrid;
		startX = partX + Margin * 15;
		startY = partY + 6 * Margin;
		
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

		tableSize = (HalfPartY * 2 - 12 * Margin);

		endX = startX + tableSize;
		endY = startY + tableSize;
		squareSize = tableSize / table.length;
	}
}
