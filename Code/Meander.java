//***************************************
//* This class defines a meander and needed methods
//***************************************
public class Meander
{
	int size, currentIndex;
	int[] oneFirst;
	int[] permutation;
	MeanderCurve[] links;
	boolean meander;
	PermutationArray origin;
	String warning = "";

	public Meander(PermutationArray n)
	{
		origin = n;
		this.size = n.size;
		
		//re-write the permutation, making it starting from number 1
		n.reArrangement();
		this.oneFirst = n.cyclic;
		this.permutation = n.permutation;
		links = new MeanderCurve[this.size];

		//check if a meander exist.
		meander = meander();
	}

	public boolean meander()
	{
		if (this.size % 2 == 1)
		{
			warning = "The number of numbers in permutation must be even!";
			return false;
		}

		for (int i = 0; i < this.size; i += 2)
		{
			if (this.oneFirst[i] % 2 == 0)	//after re-writing, the number 1 is always the first number
			{
				warning = "The permutation must alternate between odd and even numbers";
				return false;
			}
		}

		for (int i = 1; i < this.size; i += 2)
		{
			if (this.oneFirst[i] % 2 == 1)
			{
				warning = "The permutation must alternate between odd and even numbers";
				return false;
			}
		}

		for (int i = 0; i < this.size - 1; i++)
		{
			MeanderCurve temp = new MeanderCurve(this.oneFirst[i], this.oneFirst[i + 1]);
			if (!addLink(temp))		//check if new added curve crosses with existing curves
			{
				warning = "There is at least one crossing!";
				return false;
			}
		}

		MeanderCurve temp = new MeanderCurve(this.oneFirst[this.size - 1], this.oneFirst[0]);
		if (!addLink(temp))
		{
			warning = "There is at least one crossing!";
			return false;
		}

		return true;
	}

	private boolean addLink(MeanderCurve temp)
	{
		// a curve or path connecting 2 neighbors has no chance to cross with other curves/paths
		if (Math.abs(temp.source - temp.destination) == 1)
		{
			links[this.currentIndex++] = temp;
			return true;
		}

		for (int i = 0; i < this.currentIndex; i++)
		{
			if (this.links[i].crossing(temp))
			{
				return false;
			}
		}

		links[this.currentIndex++] = temp;
		return true;
	}

}
