//****************************************
//* Defines all methods used to visualize an inverse, inversion table or factorid number.
//****************************************
public class NumberStringCanvas implements FixedValues
{
	String title = "", title2 = "";
	int titleX, titleY;
	int startX, startY;
	int[] permutation;
	String numbers = "";
	boolean factoradic;

	public NumberStringCanvas(int type, int partX, int partY, int[] p, long order)
	{
		this.permutation = p;
		factoradic = false;	//factoradic number needs one more parameter than other 2 types

		if (partX == Part4X && partY == Part4Y)
		{
			titleX = partX + Part5Length / 2 + 20;
			titleY = partY + 30;
		} else
		{
			titleX = partX + 20;
			titleY = partY + 30;
		}

		startY = partY + HalfPartY + 20;
		startX = partX + (HalfPartX - NumberDistance * (this.permutation.length / 2 + 1));

		for (int i = 0; i < this.permutation.length; i++)
		{
			numbers += (this.permutation[i] + "  ");
		}

		switch (type)
		{
		case INVERSE:
			title += "The permutation's inverse is:";
			break;
		case INVERSIONTABLE:
			title += "The inversion table is:";
			break;
		case FACTORADIC:
			title += "The factoradic number of " + order + "th permutation";
			title2 += "on " + p.length + " numbers is:";
			numbers += " !";
			factoradic = true;
			break;
		default:
			break;
		}
	}
}
