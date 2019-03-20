//*****************************************
//* A panel for GUI.
//* A DrawPanel used to draw 5 parts and permutations.
//* A ButtonPanel contains all needed buttons.
//* A ShowPanel shows examples for each objects.
//*****************************************
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PermutationPanel extends JPanel implements FixedValues
{
	String input, cyclicNotation, hint;
	JLabel text;
	
	PermutationArray currentPermutation;
	CyclicNotation currentCyclic;
	PermutationGraphCanvas[] graphCanvas;
	SquareGridCanvas[] gridCanvas;
	NumberStringCanvas[] numberCanvas;
	MeanderCanvas[] meanderCanvas;
	GridTourCanvas[] gridTourCanvas;
	int inputDone, numberStringIndex, squareGridIndex, pGraphIndex, meanderIndex, gridTourIndex;
	
	boolean typeOn = false, inputOn = false;
	
	JButton start, userInput, random, inverse, inversion, squareGrid, pGraph, factoradic, meander, gridTour;
	DrawPanel drawPanel;
	ButtonPanel buttonPanel;
	ShowPanel showPanel;
	
	ActionListener actionListener = new ButtonListener();
	KeyListener numberListener = new NumberListener();
	MouseListener partListener = new PartListener();
	
	//record user input
	int[] numbers;	
	int currentIndex, digit = 0, chosenPart;

	//gif files showing examples of grid tour, square grid, meander and permutation graph
	ImageIcon gridTourIcon = new ImageIcon("gridTour.gif");
	ImageIcon gridIcon = new ImageIcon("grid.gif");
	ImageIcon meanderIcon = new ImageIcon("meander.gif");
	ImageIcon graphIcon = new ImageIcon("graph.gif");

	public PermutationPanel()
	{
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(ScreenWidth + 300, ScreenHeight));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		hint = "Welcome!";

		input = OriginInput;
		cyclicNotation = "";

		//we have only 4 parts, each part can hold any kind of canvas
		graphCanvas = new PermutationGraphCanvas[4];
		gridTourCanvas = new GridTourCanvas[4];
		gridCanvas = new SquareGridCanvas[4];
		numberCanvas = new NumberStringCanvas[4];
		meanderCanvas = new MeanderCanvas[4];
		numberStringIndex = squareGridIndex = pGraphIndex = meanderIndex = gridTourIndex = 0;

		drawPanel = new DrawPanel();
		buttonPanel = new ButtonPanel();
		showPanel = new ShowPanel();

		add(drawPanel);
		add(buttonPanel);
		add(showPanel);
	}

	//show the examples
	public class ShowPanel extends JPanel implements FixedValues
	{
		int distance;

		public ShowPanel()
		{
			this.setBackground(Color.WHITE);
			setPreferredSize(new Dimension(150, ScreenHeight));
			distance = ScreenHeight / 7;
		}

		public void paintComponent(Graphics page)
		{
			super.paintComponent(page);
			page.setFont(new Font("Arial", Font.BOLD, 14));
			page.drawString("Grid Tour", 5, 25);
			gridTourIcon.paintIcon(this, page, 5, 25 + distance * 0);

			page.drawString("Square Grid", 5, 25 * 2 + distance * 1);
			gridIcon.paintIcon(this, page, 5, 25 * 2 + distance * 1);

			page.drawString("Meander", 5, 25 * 3 + distance * 2);
			meanderIcon.paintIcon(this, page, 5, 25 * 3 + distance * 2);

			page.drawString("Permutation Graph", 5, 25 * 4 + distance * 3);
			graphIcon.paintIcon(this, page, 5, 25 * 4 + distance * 3);

			page.drawString("Inverse", 5, 25 * 5 + distance * 4);
			page.setFont(new Font("Arial", Font.BOLD, 16));
			page.drawString("3 4 1 2", 5 + 30, 25 * 5 + distance * 4 + 25);

			page.setFont(new Font("Arial", Font.BOLD, 14));
			page.drawString("Inversion Table", 5, 25 * 6 + distance * 9 / 2);
			page.setFont(new Font("Arial", Font.BOLD, 16));
			page.drawString("2 2 0 0", 5 + 30, 25 * 6 + distance * 9 / 2 + 25);

			page.setFont(new Font("Arial", Font.BOLD, 14));
			page.drawString("Factoradic Number", 5, 25 * 7 + distance * 5);
			page.setFont(new Font("Arial", Font.BOLD, 16));
			page.drawString("2 2 0 0 !", 5 + 30, 25 * 7 + distance * 5 + 25);
		}
	}

	public class ButtonPanel extends JPanel implements FixedValues
	{
		public ButtonPanel()
		{
			setBackground(Color.white);
			setPreferredSize(new Dimension(150, ScreenHeight));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			JPanel p = new JPanel(); // This panel is used to give user some information.
			p.setBackground(Color.white);
			p.setPreferredSize(new Dimension(150, 2 * ScreenHeight / 5));
			p.setLayout(new GridLayout(1, 1));

			text = new JLabel("<html>" + hint + "<html>");
			text.setFont(new Font("Arial", Font.BOLD, 24));
			text.setForeground(Color.red);
			p.add(text);

			add(p);

			start = new JButton("<html>" + "New Game" + "<html>"); // Start button is used to start program.
			start.setEnabled(true);
			start.setFont(new Font("Arial", Font.BOLD, 18));
			start.addActionListener(actionListener);

			userInput = new JButton("<html>" + "Input a permutation" + "<html>");
			userInput.setEnabled(false);
			userInput.setFont(new Font("Arial", Font.BOLD, 18));
			userInput.addActionListener(actionListener);

			random = new JButton("<html>" + "Random permutation" + "<html>");
			random.setEnabled(false);
			random.setFont(new Font("Arial", Font.BOLD, 18));
			random.addActionListener(actionListener);

			inverse = new JButton("<html>" + "Inverse" + "<html>");
			inverse.setEnabled(typeOn);
			inverse.setFont(new Font("Arial", Font.BOLD, 18));
			inverse.addActionListener(actionListener);

			inversion = new JButton("<html>" + "Inversion Table" + "<html>");
			inversion.setEnabled(typeOn);
			inversion.setFont(new Font("Arial", Font.BOLD, 18));
			inversion.addActionListener(actionListener);

			squareGrid = new JButton("<html>" + "Square Grid" + "<html>");
			squareGrid.setEnabled(typeOn);
			squareGrid.setFont(new Font("Arial", Font.BOLD, 18));
			squareGrid.addActionListener(actionListener);

			factoradic = new JButton("<html>" + "Factoradic Number" + "<html>");
			factoradic.setEnabled(typeOn);
			factoradic.setFont(new Font("Arial", Font.BOLD, 18));
			factoradic.addActionListener(actionListener);

			pGraph = new JButton("<html>" + "Permutation Graph" + "<html>");
			pGraph.setEnabled(typeOn);
			pGraph.setFont(new Font("Arial", Font.BOLD, 18));
			pGraph.addActionListener(actionListener);

			meander = new JButton("<html>" + "Meander" + "<html>");
			meander.setEnabled(typeOn);
			meander.setFont(new Font("Arial", Font.BOLD, 18));
			meander.addActionListener(actionListener);

			gridTour = new JButton("<html>" + "Grid Tour" + "<html>");
			gridTour.setEnabled(typeOn);
			gridTour.setFont(new Font("Arial", Font.BOLD, 18));
			gridTour.addActionListener(actionListener);

			JPanel p2 = new JPanel();
			p2.setBackground(Color.white);
			p2.setPreferredSize(new Dimension(150, 3 * ScreenHeight / 5));
			p2.setLayout(new GridLayout(10, 1));
			p2.add(start);
			p2.add(userInput);
			p2.add(random);
			p2.add(inverse);
			p2.add(inversion);
			p2.add(squareGrid);
			p2.add(factoradic);
			p2.add(pGraph);
			p2.add(meander);
			p2.add(gridTour);

			add(p2);
			this.addKeyListener(numberListener);
			this.setFocusable(true);
			this.requestFocus();
		}

		public void setHint()
		{
			text.setText("<html>" + hint + "<html>");
		}

		public void setTypeOn()
		{
			inverse.setEnabled(typeOn);
			inversion.setEnabled(typeOn);
			pGraph.setEnabled(typeOn);
			squareGrid.setEnabled(typeOn);
			factoradic.setEnabled(typeOn);
			meander.setEnabled(typeOn);
			gridTour.setEnabled(typeOn);
		}
	}

	//for user to choose either part
	public class PartListener extends MouseAdapter
	{
		public void mousePressed(MouseEvent event)
		{
			int x = event.getX();
			int y = event.getY();

			if (x > Part1X && x < Part2X)
			{
				if (y > 0 && y < Part3Y)
				{
					chosenPart = 1;
				} else if (y > Part3Y && y < ScreenHeight)
				{
					chosenPart = 3;
				} else
				{
					hint = "Press within part to chose it!";
					buttonPanel.setHint();
				}
			} else if (x > Part2X && x < ScreenWidth)
			{
				if (y > 0 && y < Part3Y)
				{
					chosenPart = 2;
				} else if (y > Part3Y && y < ScreenHeight)
				{
					chosenPart = 4;
				} else
				{
					hint = "Press within part to chose it!";
					buttonPanel.setHint();
				}
			} else
			{
				hint = "Press within part to chose it!";
				buttonPanel.setHint();
			}
		}
	}

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();

			if (source == start)	//start a new game, reset all variables to origin
			{
				input = OriginInput;
				cyclicNotation = "";
				userInput.setEnabled(true);
				random.setEnabled(true);
				hint = "Input by yourself or generate a random permutation.";
				buttonPanel.setHint();
				typeOn = false;
				buttonPanel.setTypeOn();
				drawPanel.repaint();
				numbers = new int[12];
				currentIndex = 0;
				graphCanvas = new PermutationGraphCanvas[4];
				gridTourCanvas = new GridTourCanvas[4];
				gridCanvas = new SquareGridCanvas[4];
				numberCanvas = new NumberStringCanvas[4];
				meanderCanvas = new MeanderCanvas[4];
				numberStringIndex = squareGridIndex = pGraphIndex = meanderIndex = gridTourIndex = 0;
				chosenPart = 0;
			} else if (source == userInput)	//user decides to input a permutation or finishes inputting
			{
				inputDone++;
				inputOn = true;
				if (inputDone == 2)	//if user finishes inputting
				{
					userInput.setEnabled(false);
					inputDone = 0;
					inputOn = false;
					int[] temp = new int[currentIndex];
					for (int i = 0; i < currentIndex; i++)
					{
						temp[i] = numbers[i];
					}
					currentPermutation = new PermutationArray(temp);
					if (currentPermutation.wrongInput)	//check if this input is not a real permutation
					{
						hint = currentPermutation.warning + "Please restart.";
						buttonPanel.setHint();
						typeOn = false;
						buttonPanel.setTypeOn();
						userInput.setEnabled(false);
						random.setEnabled(false);
						numbers = new int[12];
						currentIndex = 0;
						return;
					}
					currentCyclic = new CyclicNotation(currentPermutation);
					cyclicNotation = currentCyclic.toString();
					hint = "Press part to choose it. Press button to choose a show type for each part.";
					buttonPanel.setHint();
					typeOn = true;
					buttonPanel.setTypeOn();
					drawPanel.requestFocus();
					drawPanel.repaint();
				} else	//user decides to input permutation
				{
					hint = "Press key to input numbers. A permutation can have up to 12 numbers. Use SPACE to separate numbers.";
					buttonPanel.setHint();
					input = "";
					cyclicNotation = "";
					typeOn = false;
					buttonPanel.setTypeOn();
					random.setEnabled(false);
					buttonPanel.requestFocus();
				}

			} else if (source == random)	//random generate a permutation
			{
				currentPermutation = new PermutationArray();
				input = currentPermutation.toString();
				currentCyclic = new CyclicNotation(currentPermutation);
				cyclicNotation = currentCyclic.toString();
				random.setEnabled(false);
				userInput.setEnabled(false);
				typeOn = true;
				buttonPanel.setTypeOn();
				hint = "Press part to choose it. Press button to choose a show type for each part.";
				buttonPanel.setHint();
				drawPanel.repaint();
				drawPanel.requestFocus();
			} else if (source == inverse)	
			{
				Inverse iv = new Inverse(currentPermutation);
				if (chosenPart != 0)
				{
					//add a NumberStringCanvas
					numberCanvas[numberStringIndex++] = new NumberStringCanvas(INVERSE, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1], iv.inverse, 0);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == inversion)	//inversion table
			{
				InversionTable iv = new InversionTable(currentPermutation);
				if (chosenPart != 0)
				{
					//add a NumberStringCanvas
					numberCanvas[numberStringIndex++] = new NumberStringCanvas(INVERSIONTABLE, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1], iv.inversionTable, 0);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == factoradic)
			{
				Factoradic iv = new Factoradic(currentPermutation);
				if (chosenPart != 0)
				{
					//add a NumberStringCanvas
					numberCanvas[numberStringIndex++] = new NumberStringCanvas(FACTORADIC, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1], iv.factoradic, iv.number);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == squareGrid)
			{
				SquareGrid sg = new SquareGrid(currentPermutation);
				if (chosenPart != 0)
				{
					gridCanvas[squareGridIndex++] = new SquareGridCanvas(sg, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1]);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == meander)
			{
				Meander sg = new Meander(currentPermutation);
				if (chosenPart != 0)
				{
					meanderCanvas[meanderIndex++] = new MeanderCanvas(sg, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1]);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == pGraph)	//permutation graph
			{
				PermutationGraph pg = new PermutationGraph(currentPermutation);
				if (chosenPart != 0)
				{
					graphCanvas[pGraphIndex++] = new PermutationGraphCanvas(pg, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1]);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			} else if (source == gridTour)
			{
				GridTour gt = new GridTour(currentPermutation);
				if (chosenPart != 0)
				{
					gridTourCanvas[gridTourIndex++] = new GridTourCanvas(gt, PARTS[chosenPart - 1][0],
							PARTS[chosenPart - 1][1]);
					chosenPart = 0;
					drawPanel.repaint();
					drawPanel.requestFocus();
				}
			}
		}
	}

	public class NumberListener implements KeyListener
	{
		public void keyPressed(KeyEvent event)
		{
			if (currentIndex >= 12)
			{
				hint = "Cannot input more than 12 numbers!";
				buttonPanel.setHint();
				return;
			}

			if (inputOn)
			{
				hint = "Input a number";
				buttonPanel.setHint();
				switch (event.getKeyCode())
				{
				case KeyEvent.VK_SPACE:
					if (digit > 12 || digit < 1)
					{
						hint = "The number must be within 1 and 12!";
						buttonPanel.setHint();
						return;
					}
					if (digit != 0)
					{
						numbers[currentIndex++] = digit;
						digit = 0;
					}
					break;
				case KeyEvent.VK_0:
					digit = digit * 10;
					break;
				case KeyEvent.VK_1:
					digit = digit * 10 + 1;
					break;
				case KeyEvent.VK_2:
					digit = digit * 10 + 2;
					break;
				case KeyEvent.VK_3:
					digit = digit * 10 + 3;
					break;
				case KeyEvent.VK_4:
					digit = digit * 10 + 4;
					break;
				case KeyEvent.VK_5:
					digit = digit * 10 + 5;
					break;
				case KeyEvent.VK_6:
					digit = digit * 10 + 6;
					break;
				case KeyEvent.VK_7:
					digit = digit * 10 + 7;
					break;
				case KeyEvent.VK_8:
					digit = digit * 10 + 8;
					break;
				case KeyEvent.VK_9:
					digit = digit * 10 + 9;
					break;
				default:
					hint = "Input a digit or a space please!";
					buttonPanel.setHint();
					break;
				}

				input = "";
				for (int i = 0; i < currentIndex; i++)
				{
					input += numbers[i] + " ";
				}

				if (digit > 12 || digit < 0)
				{
					hint = "The number must be within 1 and 12!";
					buttonPanel.setHint();
					digit = 0;
				}
				if (digit != 0)
				{
					input += digit;
				}
				drawPanel.repaint();
				buttonPanel.requestFocus();
			}
		}

		public void keyReleased(KeyEvent arg0)
		{
		}

		public void keyTyped(KeyEvent arg0)
		{
		}
	}

	public class DrawPanel extends JPanel implements FixedValues
	{
		public DrawPanel()
		{
			this.setBackground(Color.WHITE);
			this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
			this.addMouseListener(partListener);
			this.setFocusable(true);
		}

		public void paintComponent(Graphics page)
		{
			super.paintComponent(page);

			page.setColor(Color.BLACK);

			//separate screen into 5 parts
			page.drawRect(Margin, 0, ScreenWidth - Margin * 2, ScreenHeight - Margin);
			page.drawLine(Part3X, Part3Y, ScreenWidth - Margin, Part4Y);
			page.drawLine(Part2X, Part2Y, Part4X, ScreenHeight - Margin);
			page.setColor(Color.white);
			page.fillRect(Part5X, Part5Y, Part5Length, Part5Height);
			page.setColor(Color.black);
			page.drawRect(Part5X, Part5Y, Part5Length, Part5Height);
			
			//write permutation and the cyclic notation
			page.setFont(new Font("Arial", Font.BOLD, 20));
			page.drawString("Permutation:", Part5X + 10, Part5Y + 25);
			page.drawString(input, Part5X + 15, Part5Y + 60);
			page.drawString("Cyclic Notation:", Part5X + 10, Part5Y + 25 + Part5Height / 2);
			page.drawString(cyclicNotation, Part5X + 5, Part5Y + 60 + Part5Height / 2);

			//if user chooses to add NumberStringCanvas
			for (int k = 0; k < numberStringIndex; k++)
			{
				this.paintNumberString(page, numberCanvas[k]);
			}

			//if users adds SquareGridCanvas
			for (int k = 0; k < squareGridIndex; k++)
			{
				this.paintSquareGrid(page, gridCanvas[k]);
			}

			//if users adds MeanderCanvas
			for (int k = 0; k < meanderIndex; k++)
			{
				this.paintMeander(page, meanderCanvas[k]);
			}

			//if users adds PermutationGraphCanvas
			for (int k = 0; k < pGraphIndex; k++)
			{
				this.paintPermutationGraph(page, graphCanvas[k]);
			}

			//if users adds GridTourCanvas
			for (int k = 0; k < gridTourIndex; k++)
			{
				this.paintGridTour(page, gridTourCanvas[k]);
				;
			}
		}

		public void paintGridTour(Graphics page, GridTourCanvas canvas)
		{
			page.setColor(Color.BLACK);
			page.drawString(canvas.title, canvas.titleX, canvas.titleY);

			if (canvas.tour.gridTour)
			{
				page.drawString("          " + canvas.tour.columnToString(), canvas.titleX, canvas.titleY + 30);

				page.setColor(Color.yellow);
				for (int i = 0; i < canvas.size; i++)
				{
					page.drawLine(canvas.startX + i * canvas.lineDistance, canvas.startY,
							canvas.startX + i * canvas.lineDistance,
							canvas.startY + (canvas.size - 1) * canvas.lineDistance);
					page.drawLine(canvas.startX, canvas.startY + i * canvas.lineDistance,
							canvas.startX + (canvas.size - 1) * canvas.lineDistance,
							canvas.startY + i * canvas.lineDistance);
				}
				page.setColor(Color.BLACK);
				for (int i = 0; i < canvas.paths.length; i++)
				{
					GridTourPath temp = canvas.paths[i];
					page.drawLine(temp.source.x, temp.source.y, temp.destination.x, temp.destination.y);
					page.drawLine(temp.destination.x, temp.destination.y, temp.arrowX1, temp.arrowY1);
					page.drawLine(temp.destination.x, temp.destination.y, temp.arrowX2, temp.arrowY2);
				}
			} else
			{
				page.drawString(canvas.tour.warning, canvas.startX - 70, canvas.startY + HalfPartY - 90);
			}
		}

		public void paintMeander(Graphics page, MeanderCanvas canvas)
		{
			page.setColor(Color.black);
			page.drawString(canvas.title, canvas.titleX, canvas.titleY);
			if (canvas.meander.meander)
			{
				for (int i = 0; i < canvas.size; i++)
				{
					page.drawString(canvas.permutation[i] + "", canvas.startX + canvas.numberDistance * i,
							canvas.startY);
				}

				for (int i = 0; i < canvas.size; i++)
				{
					page.drawArc(canvas.curves[i].arcStartX, canvas.curves[i].arcStartY, canvas.curves[i].arcWidth,
							canvas.curves[i].arcHeight, canvas.curves[i].arcStartAngle, canvas.curves[i].arcAngle);
				}
			} else
			{
				page.drawString(canvas.meander.warning, canvas.startX - 50, canvas.startY);
			}
		}

		public void paintPermutationGraph(Graphics page, PermutationGraphCanvas canvas)
		{
			page.setColor(Color.black);
			page.drawString(canvas.title, canvas.titleX, canvas.titleY);
			for (int i = 0; i < canvas.edges.length; i++)
			{
				int source = canvas.edges[i].source - 1;
				int destination = canvas.edges[i].destination - 1;
				page.drawLine(canvas.points[source].x, canvas.points[source].y, canvas.points[destination].x,
						canvas.points[destination].y);
			}
			for (int i = 0; i < canvas.count; i++)
			{
				page.setColor(Color.WHITE);
				page.fillOval(canvas.points[i].x - NumberCircleSize / 2, canvas.points[i].y - NumberCircleSize / 2,
						NumberCircleSize, NumberCircleSize);
				page.setColor(Color.BLACK);
				page.drawOval(canvas.points[i].x - NumberCircleSize / 2, canvas.points[i].y - NumberCircleSize / 2,
						NumberCircleSize, NumberCircleSize);
				page.drawString(i + 1 + "", canvas.points[i].x - 7, canvas.points[i].y + 8);
			}
		}

		public void paintNumberString(Graphics page, NumberStringCanvas canvas)
		{
			page.setColor(Color.black);
			page.drawString(canvas.title, canvas.titleX, canvas.titleY);
			page.drawString(canvas.numbers, canvas.startX, canvas.startY);
			if (canvas.factoradic)
			{
				page.drawString(canvas.title2, canvas.titleX, canvas.titleY + 30);
			}
		}

		public void paintSquareGrid(Graphics page, SquareGridCanvas canvas)
		{
			page.setColor(Color.black);
			page.drawString(canvas.title, canvas.titleX, canvas.titleY);

			for (int row = 0; row < canvas.table.length; row++)
			{
				for (int column = 0; column < canvas.table.length; column++)
				{
					if (canvas.table[row][column] == 0)
					{
						page.setColor(Color.red);
						page.drawLine(canvas.startX + column * canvas.squareSize + 3,
								canvas.startY + row * canvas.squareSize + 3,
								canvas.startX + (column + 1) * canvas.squareSize - 3,
								canvas.startY + (row + 1) * canvas.squareSize - 3);
						page.drawLine(canvas.startX + (column + 1) * canvas.squareSize - 3,
								canvas.startY + row * canvas.squareSize + 3,
								canvas.startX + column * canvas.squareSize + 3,
								canvas.startY + (row + 1) * canvas.squareSize - 3);
					}
					if (canvas.table[row][column] > 0)
					{
						page.setColor(Color.GREEN);
						page.fillRect(canvas.startX + column * canvas.squareSize,
								canvas.startY + row * canvas.squareSize, canvas.squareSize, canvas.squareSize);
					}

					page.setColor(Color.BLACK);
					page.drawRect(canvas.startX + column * canvas.squareSize, canvas.startY + row * canvas.squareSize,
							canvas.squareSize, canvas.squareSize);
				}
			}
		}
	}
}
