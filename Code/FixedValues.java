
//*******************************
//* This class defines all common values used in the project.
//*******************************

import java.awt.Font;

public interface FixedValues
{
	int ScreenWidth = 1600;
	int ScreenHeight = 1000;
	int NumberDistance = 25;
	int Margin = 10;

	int Part1X = Margin;
	int Part1Y = 0;

	int HalfPartX = (ScreenWidth - 6 * Margin) / 4;
	int HalfPartY = (ScreenHeight) / 4;

	int Part5Length = 400;
	int Part5Height = 150;
	int Part5X = ScreenWidth / 2 - Part5Length / 2;
	int Part5Y = ScreenHeight / 2 - Part5Height / 2;

	int Part2X = ScreenWidth / 2;
	int Part2Y = 0;

	int Part3X = Margin;
	int Part3Y = ScreenHeight / 2;

	int Part4X = ScreenWidth / 2;
	int Part4Y = ScreenHeight / 2;

	int[][] PARTS =
	{
			{ Part1X, Part1Y },
			{ Part2X, Part2Y },
			{ Part3X, Part3Y },
			{ Part4X, Part4Y } };

	int GraphSize = (HalfPartY - 6 * Margin) * 2;
	int NumberCircleSize = 35;

	String OriginInput = "Please input a permutation!";

	int Max = 12;
	int Min = 3;

	Font TitleFont = new Font("Arial", Font.BOLD, 20);

	int INVERSE = 1;
	int INVERSIONTABLE = 2;
	int FACTORADIC = 3;
}
