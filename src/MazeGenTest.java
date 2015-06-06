import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class MazeGenTest
{
	@Test
	public void test()
	{
		// Testing mazer with size 1 that cannot have its walls cut
		Maze maze1 = MazeGen.getInstance().generate(1);

		Block[][] blocksMaze1 = maze1.getBlocks();

		if (!(blocksMaze1[0][0].getWalls()[0] == 1
				&& blocksMaze1[0][0].getWalls()[1] == 1
				&& blocksMaze1[0][0].getWalls()[2] == 1 && blocksMaze1[0][0]
					.getWalls()[3] == 1))
		{
			fail();
		}
		// Testing a maze with size larger than 1
		Maze maze2 = MazeGen.getInstance().generate(9);
		if (!reacheAblePoint(maze2.getBlocks(), new int[]
		{ 0, 0 }, new int[]
		{ 4, 4 }, new ArrayList<int[]>()))
		{
			fail();
		}
	}

	private boolean reacheAblePoint(Block[][] blocks, int[] start, int[] exit,
			List<int[]> steps)
	{
		if (!(start[0] == exit[0] && start[1] == exit[1]))
		{
			steps.add(start);
			for (int i = 0; i < 2; i++)
			{
				for (int j = 0; j < 2; j++)
				{
					int[] nextMove =
					{ i, j };

					if (isValidMove(blocks, start, nextMove))
					{
						if (hasStep(steps, nextMove))
						{
							return reacheAblePoint(blocks, nextMove, exit, steps);
						}
					}
				}
			}
		}
		return true;
	}

	private boolean isValidMove(Block[][] blocks, int[] actual, int[] next)
	{
		int a = actual[0];
		int b = actual[1];
		int c = next[0];
		int d = next[1];
		// Going Up
		if (c == a + 1)
		{
			if (blocks[a][b].getWalls()[0] == 0
					&& blocks[c][d].getWalls()[1] == 0)
			{
				return true;
			}
			// Going Down
		} else if (c == a - 1)
		{
			if (blocks[a][b].getWalls()[1] == 0
					&& blocks[c][d].getWalls()[0] == 0)
			{
				return true;
			}
			// Going right
		} else if (d == b + 1)
		{
			if (blocks[a][b].getWalls()[3] == 0
					&& blocks[c][d].getWalls()[2] == 0)
			{
				return true;
			}
			// Going left
		} else if (d == b - 1)
		{
			if (blocks[a][b].getWalls()[2] == 0
					&& blocks[c][d].getWalls()[3] == 0)
			{
				return true;
			}
		}
		return false;
	}

	private boolean hasStep(List<int[]> steps, int[] step)
	{
		for (int i = 0; i < steps.size(); i++)
		{
			if (steps.get(i)[0] == step[0] && steps.get(i)[1] == step[1])
			{
				return true;
			}
		}
		return false;
	}
}
