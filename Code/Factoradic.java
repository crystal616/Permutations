
public class Factoradic
{
	int[] factoradic;
	int current;
	long number;
	String title;

	public Factoradic(PermutationArray n)
	{
		factoradic = (new InversionTable(n)).inversionTable; // factoradic number equals to the inversion table

		number = 0;
		for (int i = 0; i < this.factoradic.length; i++) // transfer the factoradic number to the corresponding base 10
															// number
		{
			number += this.factoradic[i] * factorial(this.factoradic.length - 1 - i);
		}

		title = "The factoradic number of " + this.number;
		title += ( "th permutation on " + this.factoradic.length + " numbers is:");
	}

	// return the factorial of a number.
	private int factorial(int i)
	{
		if (i == 0)
		{
			return 1;
		} else
			return i * factorial(i - 1);
	}
}
