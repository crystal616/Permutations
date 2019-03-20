//**********************************
//* A permutation graph visualization class.
//* A helper class Circle is used. It represents a node of the graph
//**********************************
public class PermutationGraphCanvas implements FixedValues
{
	Circle[] points;
//	int[] permutation;
	int centerX, centerY, count;
	PermutationGraphEdge[] edges;
	String title = "Permutation Graph: ";
	int titleX, titleY;

	public PermutationGraphCanvas(PermutationGraph n, int partX, int partY)
	{
		edges = new PermutationGraphEdge[n.currentIndex];
		for (int i = 0; i < n.currentIndex; i++)
		{
			edges[i] = n.edges[i];
		}
		count = n.permutation.length;

		if (partX == Part4X && partY == Part4Y)
		{
			titleX = partX + Part5Length / 2 + 20;
			titleY = partY + 30;
		} else
		{
			titleX = partX + 20;
			titleY = partY + 30;
		}

		centerX = partX + HalfPartX;	//nodes are placed on a circle
		centerY = partY + HalfPartY;
		int radius = GraphSize / 2;	
		
		double theta = 360 / count;		//nodes are evenly placed
		double[] angles = new double[count];
		points = new Circle[count];

		for (int i = 0; i < count; i++)	//set parameters for each Circle
		{
			angles[i] = Math.toRadians(theta * i);
			points[i] = new Circle((int) (centerX + radius * Math.cos(angles[i])),
					(int) (centerY - radius * Math.sin(angles[i])));
		}

	}

	public class Circle
	{
		int x, y;
		int d = NumberCircleSize;	//circle diameter

		public Circle(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
