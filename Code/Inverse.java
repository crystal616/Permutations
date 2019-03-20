//***************************************
//* This class generates the inverse of a permutation.
//***************************************
public class Inverse
{
	int[] inverse;

	public Inverse(PermutationArray n)
	{
		inverse = new int[n.size];
		
		for (int i=0; i<n.size; i++) {
			int index = n.permutation[i];
			inverse[index - 1] = i + 1;
		}
	}
	
	public String toString() {
		String result = "";
		for (int i=0; i<this.inverse.length; i++) {
			result += ("  " + this.inverse[i]);
		}
		return result;
	}
}
