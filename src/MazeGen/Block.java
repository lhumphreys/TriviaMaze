package MazeGen;

import java.io.Serializable;

public class Block implements Serializable{
	private int[] walls;
	private boolean isCut;

	// Cannot be instantiated used outside its package
	protected Block() {
		this.isCut = false;
		this.walls = new int[] { 1, 1, 1, 1 };
	}

	// The methods Block* are meant to provide a easy way to block open walls
	public void blockUp() {
		walls[0] = 1;
	}

	public void blockDown() {
		walls[1] = 1;
	}

	public void blockLeft() {
		walls[2] = 1;
	}

	public void blockRight() {
		walls[3] = 1;
	}

	// The methods isBlocked* are meant to provide a easy way to check whether a
	// path is blocked
	public boolean isBlockedUp() {
		return walls[0] == 1;
	}

	public boolean isBlockedDown() {
		return walls[1] == 1;
	}

	public boolean isBlockedLeft() {
		return walls[2] == 1;
	}

	public boolean isBlockedRight() {
		return walls[3] == 1;
	}

	public void cutRight() {
		walls[3] = 0;
	}

	public void cutLeft() {
		walls[2] = 0;
	}

	public void cutUp() {
		walls[0] = 0;
	}

	public void cutDown() {
		walls[1] = 0;
	}

	public boolean isCut() {
		return isCut;
	}

	public void setCut(boolean isCut) {
		this.isCut = isCut;
	}

	@Override
	public String toString() {
		String block = "";

		block += walls[0];
		block += walls[1];
		block += walls[2];
		block += walls[3];
		return block;
	}
}
