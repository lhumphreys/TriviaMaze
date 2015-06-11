/*
 * Team Name: The Illuminati
 * Author: Augusto Melo
 * Date Modified: 6/10/2015
 * CSCD 350
 * 
 * Block represents one room of the maze.
 * 
 */

package maze;

import java.io.Serializable;

public class Block implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] walls;
	private boolean isCut;

	// Cannot be instantiated used outside its package
	protected Block() 
	{
		this.isCut = false;
		this.walls = new int[] { 1, 1, 1, 1 };
	}

	// The methods Block* are meant to provide a easy way to block open walls
	public void blockUp() 
	{
		walls[0] = 1;
	}

	public void blockDown() 
	{
		walls[1] = 1;
	}

	public void blockLeft() 
	{
		walls[2] = 1;
	}

	public void blockRight() 
	{
		walls[3] = 1;
	}

	// The methods isBlocked* are meant to provide a easy way to check whether a
	// path is blocked
	public boolean isBlockedUp() 
	{
		return walls[0] == 1;
	}

	public boolean isBlockedDown() 
	{
		return walls[1] == 1;
	}

	public boolean isBlockedLeft() 
	{
		return walls[2] == 1;
	}

	public boolean isBlockedRight() 
	{
		return walls[3] == 1;
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

	public boolean isCut() 
	{
		return isCut;
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
	
	public void printRoom()
	{
		String[][] room = new String[6][6];
		
		for(int i = 0; i < 6; i ++)
		{
			for(int j = 0; j < 6; j++)
			{
				room[i][j] = "  ";
			}
		}
		
		for(int i = 1; i < 5; i++)
		{
			room[0][i] = "--";
			room[5][i] = "--";
			room[i][0] = " |";
			room[i][5] = "| ";
		}
		
		if(this.walls[0] == 0)
		{
			room[0][2] = "  ";
			room[0][3] = "  ";
		}
		if(this.walls[1] == 0)
		{
			room[5][2] = "  ";
			room[5][3] = "  ";
		}
		if(this.walls[2] == 0)
		{
			room[2][0] = "  ";
			room[3][0] = "  ";
		}
		if(this.walls[3] == 0)
		{
			room[2][5] = "  ";
			room[3][5] = "  ";
		}
		
		
		for(int i = 0; i < 6; i ++)
		{
			for(int j = 0; j < 6; j ++)
			{
				System.out.print(room[i][j]);
			}
			System.out.println();
		}
	}
}
