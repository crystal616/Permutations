//************************************
//* A helper class needed by class Meander and MeanderCanvas.
//************************************
public class MeanderCurve
{
	int source, destination;
	int max, min;
	int arcStartX, arcStartY, arcStartAngle, arcAngle, arcWidth, arcHeight;		//parameters define an arc

	public MeanderCurve(int s, int d)
	{
		source = s; 		// a path from s to d
		destination = d;
		max = Math.max(source, destination);
		min = Math.min(source, destination);

		if (this.source % 2 == 1) 		// if this path is from an even number to an odd number
		{
			arcStartAngle = Math.max(20 - 5 * (this.max - this.min) / 2, 3);
			arcAngle = 180 - 2 * this.arcStartAngle;
		} else							//if this path is from an odd number to an even number
		{
			arcStartAngle = Math.max(20 - 5 * (this.max - this.min) / 2, 3) * -1;
			arcAngle = -180 - 2 * this.arcStartAngle;
		}
	}

	public void setParameter(int sx, int sy, int w, int h)	//set parameters for arc which represents a path
	{
		this.arcStartX = sx;

		if (this.source % 2 == 1)
		{
			this.arcStartY = sy - 15;
		} else
		{
			this.arcStartY = sy;
		}

		this.arcWidth = w;
		this.arcHeight = h;
	}

	//check if a crossing exists 
	public boolean crossing(MeanderCurve c)
	{
		// check if those curves are on the same side
		if (this.source % 2 == 1 && c.source % 2 != 1)
		{
			return false;
		}
		if (this.source % 2 != 1 && c.source % 2 == 1)
		{
			return false;
		}

		//check if they cross
		if (this.min < c.min && this.max > c.min && this.max < c.max)
		{
			return true;
		}

		if (c.min < this.min && c.max > this.min && c.max < this.max)
		{
			return true;
		}

		return false;
	}

	
	public String toString()
	{
		return this.source + "\t" + this.destination + "\t" + (this.max - this.min);
	}

}