import java.util.Iterator;

public class Maze
{
	private Block[][] blocks;
	private int[] end;
	private int[] start;
	private int[] playerPosition;

	public Maze(Block[][] blocks)
	{
		this.blocks = blocks;
	}

	// Returns true if the player has reached the exit
	public boolean move(int[] coordinates)
	{
		// ONLY HORIZONTAL AND VERTICAL COORDINATES!
		this.playerPosition = coordinates;
		if (coordinates[0] == end[0] && coordinates[1] == end[1])
		{
			return true;
		}
		return false;
	}

	public int[] getStart()
	{
		return start;
	}

	public void setStart(int[] start)
	{
		this.start = start;
	}

	public int[] getEnd()
	{
		return end;
	}

	public void setEnd(int[] end)
	{
		this.end = end;
	}
	
	public Block[][] getBlocks(){
		return blocks;
	}

	public void printMaze()
	{
		for (int i = 0; i < blocks.length; i++)
		{
			String line = "";
			for (int j = 0; j < blocks.length; j++)
			{
				line += blocks[i][j] + " ";
			}
			System.out.println(line);
		}
	}

}
