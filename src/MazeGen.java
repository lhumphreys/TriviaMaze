import java.util.ArrayList;
import java.util.Random;

public class MazeGen
{
	private static MazeGen INSTANCE;

	public static MazeGen getInstance()
	{
		if (INSTANCE == null)
		{
			INSTANCE = new MazeGen();
		}
		return INSTANCE;
	}

	private MazeGen()
	{
		//Singleton
	}

	public Maze generate(int size)
	{
		Block[][] blocks = new Block[size][size];

		// Add Blocks to the maze
		for (int i = 0; i < blocks.length; i++)
		{
			for (int j = 0; j < blocks.length; j++)
			{
				blocks[i][j] = new Block();
			}
		}
		blocks = writePaths(blocks, new ArrayList<int[]>(), -1);

		return new Maze(blocks);
	}

	private Block[][] writePaths(Block[][] blocks, ArrayList<int[]> steps,
			int pointer)
	{
		if (pointer < 0)
		{
			pointer += 1;
			steps.add(new int[]
			{ 0, 0 });
		}

		int x = steps.get(pointer)[0];
		int y = steps.get(pointer)[1];

		int[] next = getNextStep(blocks, steps.get(pointer));

		if (!(next[0] == steps.get(0)[0] && next[1] == steps.get(0)[1]))
		{
			if (next[0] == x && next[1] == y)
			{
				blocks[x][y].setCut(true);
				writePaths(blocks, steps, pointer - 1);
			} else
			{
				if (pointer == steps.size() - 1)
				{
					steps.add(next);
				} else
				{
					steps.add(pointer + 1, next);
				}
				cutThrough(blocks, steps.get(pointer), next);
				writePaths(blocks, steps, pointer + 1);
			}
		}
		return blocks;
	}

	private int[] getNextStep(Block[][] blocks, int[] step)
	{
		ArrayList<int[]> possibleSteps = new ArrayList<int[]>();
		int x = step[0];
		int y = step[1];
		// UP
		if (y + 1 < blocks.length && !blocks[x][y + 1].isCut())
		{
			possibleSteps.add(new int[]
			{ x, y + 1 });
		}
		// DOWN
		if (y - 1 >= 0 && !blocks[x][y - 1].isCut())
		{
			possibleSteps.add(new int[]
			{ x, y - 1 });
		}
		// RIGHT
		if (x + 1 < blocks.length && !blocks[x + 1][y].isCut())
		{
			possibleSteps.add(new int[]
			{ x + 1, y });
		}
		// LEFT
		if (x - 1 >= 0 && !blocks[x - 1][y].isCut())
		{
			possibleSteps.add(new int[]
			{ x - 1, y });
		}
		// NO VALID MOVE
		if (possibleSteps.size() == 0)
		{
			return step;
		}
		// THERE IS AT LEAST ON VALID MOVE
		Random random = new Random();
		int[] next = possibleSteps.get(random.nextInt(possibleSteps.size()));
		// System.out.println(next[0]+","+next[1]);
		return next;
	}

	private static void cutThrough(Block[][] blocks, int[] actualStep,
			int[] nextStep)
	{
		int xActual = actualStep[0];
		int yActual = actualStep[1];
		int xNext = nextStep[0];
		int yNext = nextStep[1];
		if (xActual > xNext)
		{
			blocks[xActual][yActual].cutUp();
			blocks[xNext][yNext].cutDown();
		} else if (xActual < xNext)
		{
			blocks[xActual][yActual].cutDown();
			blocks[xNext][yNext].cutUp();
		} else if (yActual > yNext)
		{
			blocks[xActual][yActual].cutLeft();
			blocks[xNext][yNext].cutRight();

		} else if (yActual < yNext)
		{
			blocks[xActual][yActual].cutRight();
			blocks[xNext][yNext].cutLeft();
		}
		blocks[xActual][yActual].setCut(true);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
