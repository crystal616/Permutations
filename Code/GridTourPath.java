//**************************************
//* A helper class for class GridTour and GridTour Canvas.
//* Itself has a helper class point which represents a cross point of a row and a column.
//**************************************
public class GridTourPath
{
	point source, destination;
	int arrowX1, arrowY1, arrowX2, arrowY2;

	public GridTourPath(int row1, int column1, int row2, int column2)
	{
		//a path is a segment from "source" point to "destination" point
		this.source = new point(row1, column1);
		this.destination = new point(row2, column2);
	}

	//given the part that user chooses, set the coordinates x and y for each point and the arrow
	public void setLocations(int startX, int startY, int distance)
	{
		source.setLocation(startX + (this.source.column - 1) * distance, startY + (this.source.row - 1) * distance);
		destination.setLocation(startX + (this.destination.column - 1) * distance,
				startY + (this.destination.row - 1) * distance);

		if (this.source.row == this.destination.row)
		{
			if(this.source.column<this.destination.column) {
				arrowX1 = this.destination.x - 10;
				arrowY1 = this.destination.y - 10;
				arrowX2 = this.destination.x - 10;
				arrowY2 = this.destination.y + 10;
			}else {
				arrowX1 = this.destination.x + 10;
				arrowY1 = this.destination.y - 10;
				arrowX2 = this.destination.x + 10;
				arrowY2 = this.destination.y + 10;
			}

		}
		
		if (this.source.column == this.destination.column) {
			if (this.source.row < this.destination.row) {
				arrowX1 = this.destination.x - 10;
				arrowY1 = this.destination.y - 10;
				arrowX2 = this.destination.x + 10;
				arrowY2 = this.destination.y - 10;
			}else {
				arrowX1 = this.destination.x - 10;
				arrowY1 = this.destination.y + 10;
				arrowX2 = this.destination.x + 10;
				arrowY2 = this.destination.y + 10;
			}
		}

	}

	public class point
	{
		int row, column;
		int x, y;

		public point(int row, int column)
		{
			this.row = row;
			this.column = column;
		}

		public void setLocation(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
