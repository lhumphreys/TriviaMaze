package MazeGen;

import java.io.Serializable;
import java.security.InvalidParameterException;

public class Maze implements Serializable {
	private Block[][] blocks;
	private int[] end;
	private int[] start;
	private int[] playerPosition;
	private int mazeSize;

	// Cannot be instantiated used outside its package
	protected Maze(Block[][] blocks, int[] start, int[] end) {
		this.blocks = blocks;
		this.mazeSize = blocks.length;
		checkBounds(start);
		checkBounds(end);
		if (start[0] == end[0] && start[1] == end[1]) {
			throw new InvalidParameterException(
					"Start point is the same as the end poit");
		}
		this.start = start;
		this.end = end;
		this.playerPosition = start;
	}

	// Returns true if the player has reached the exit
	// ONLY HORIZONTAL AND VERTICAL COORDINATES!
	public boolean move(int[] coordinates) {
		checkBounds(playerPosition);
		this.playerPosition = coordinates;
		if (coordinates[0] == end[0] && coordinates[1] == end[1]) {
			return true;
		}
		return false;
	}

	public int getMazeSize() {
		return mazeSize;
	}

	public int[] getStart() {
		return start;
	}

	public int[] getEnd() {
		return end;
	}

	public Block[][] getBlocks() {
		return blocks;
	}

	public int[] getPlayerPosition() {
		return playerPosition;
	}

	// This method is for test purposes. Not used in the actual game
	public void printMazeBlocksContraints() {
		for (int i = 0; i < blocks.length; i++) {
			String line = "";
			for (int j = 0; j < blocks.length; j++) {
				line += blocks[i][j] + " ";
			}
			System.out.println(line);
		}
	}

	// Method to to help checking maze's bounds
	private void checkBounds(int[] coordinates) {
		int x = coordinates[0];
		int y = coordinates[1];
		if ((x >= mazeSize || x < 0) || (y >= mazeSize || y < 0)) {
			throw new IndexOutOfBoundsException(
					"Provided corrdinates out of maze's bounds");
		}
	}
}
