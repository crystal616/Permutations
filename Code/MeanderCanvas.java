//**************************************
//* This class defines the methods used for meander visualization.
//**************************************
public class MeanderCanvas implements FixedValues
{
	MeanderCurve[] curves;
	Meander meander;
	int[] permutation;
	int startX, startY;
	String title = "Meander:";
	int titleX, titleY;
	int numberDistance, size;
	final int totalLength = HalfPartX * 2 - Margin * 12;

	public MeanderCanvas(Meander m, int partX, int partY)
	{
		this.meander = m;
		size = m.size;
		this.permutation = new int[size];
		title += "  ( " + m.origin.toString() + ")";
		
		if (partX == Part4X && partY == Part4Y)
		{
			titleX = partX + Part5Length / 2 + 20;
			titleY = partY + 30;
		} else
		{
			titleX = partX + 20;
			titleY = partY + 30;
		}

		numberDistance = totalLength / size;
		startX = partX + Margin * 12;
		startY = partY + HalfPartY + 20;
		
		if (!m.meander) {
			return;
		}
		
		for (int i = 0; i < size; i++)
		{
			this.permutation[i] = i + 1;
		}

		curves = m.links;		

		for (int i = 0; i < this.size; i++)	//set the arc parameters for each path/curve
		{
			int left = curves[i].min;
			int right = curves[i].max;

			int x = startX + this.numberDistance * (left - 1);
			int y = startY - this.numberDistance * (right - left) / 4;
			int w = this.numberDistance * (right - left);
			int h = w/2;
			curves[i].setParameter(x, y, w, h);
		}
	}
}
