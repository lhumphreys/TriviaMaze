public class Block
{
	private int[] walls;
	private boolean isCut;

	public Block()
	{
		this.isCut = false;
		this.walls = new int[]
		{ 1, 1, 1, 1 };
	}

	public int[] getWalls()
	{
		return walls;
	}

	public boolean isCut()
	{
		return isCut;
	}

	public void cutRight()
	{
		walls[3] = 0;
	}

	public void cutLeft()
	{
		walls[2] = 0;
	}

	public void cutUp()
	{
		walls[0] = 0;
	}

	public void cutDown()
	{
		walls[1] = 0;
	}

	public void setCut(boolean isCut)
	{
		this.isCut = isCut;
	}

	@Override
	public String toString()
	{
		String block = "";
		
		block += walls[0];
		block += walls[1];
		block += walls[2];
		block += walls[3];
		return block;
	}
}
