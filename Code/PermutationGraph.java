//********************************************
//* Defines a permutation graph.
//********************************************
public class PermutationGraph
{
	PermutationGraphEdge[] edges;
	int currentIndex;
	int[] permutation;

	public PermutationGraph(PermutationArray n)
	{
		edges = new PermutationGraphEdge[n.size];
		permutation = n.permutation;		

		for (int i = 0; i < n.size; i++)
		{
			for (int j = 0; j < i; j++)
			{
				if (n.permutation[j] > n.permutation[i])	//an edge exists between a number and another number which is 
				{											//ahead of it and bigger than it
					add(new PermutationGraphEdge(n.permutation[j], n.permutation[i]));
				}
			}
		}
	}

	private void add(PermutationGraphEdge edge)
	{
		if (currentIndex >= edges.length)	//if the edge array is "full"
		{
			doubleSpace();
		}

		edges[this.currentIndex++] = edge;
	}

	private void doubleSpace()
	{
		PermutationGraphEdge[] temp = new PermutationGraphEdge[this.edges.length * 2];
		for (int i = 0; i < this.currentIndex; i++)
		{
			temp[i] = edges[i];
		}
		this.edges = temp;
	}

	@Override
	public String toString()
	{
		String result = "";
		for (int i=0; i<this.currentIndex; i++) {
			result += (this.edges[i] + "\n");
		}
		return result;
	}
	
	
}
