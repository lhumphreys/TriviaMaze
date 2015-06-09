package MazeGen;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MazeGenTest {
	@Test
	public void test() {
		// Testing mazer with size 1 that cannot have its walls cut
		/*
		 * Maze maze1 = MazeGen.getInstance().generate(1);
		 * 
		 * Block[][] blocksMaze1 = maze1.getBlocks(); Block current =
		 * blocksMaze1[0][0]; if (!(current.isBlockedUp() &&
		 * current.isBlockedDown() && current.isBlockedLeft() &&
		 * current.isBlockedRight())) { fail(); }
		 */

		// Testing a maze with size larger than 1
		Maze maze2 = MazeGen.getInstance().generate(9, new int[] { 0, 0 },
				new int[] { 8, 8 });
		if (!reacheAblePoint(maze2.getBlocks(), new int[] { 0, 0 }, new int[] {
				4, 4 }, new ArrayList<int[]>())) {
			fail();
		}
	}

	private boolean reacheAblePoint(Block[][] blocks, int[] start, int[] exit,
			List<int[]> steps) {
		if (!(start[0] == exit[0] && start[1] == exit[1])) {
			steps.add(start);
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					int[] nextMove = { i, j };

					if (isValidMove(blocks, start, nextMove)) {
						if (hasStep(steps, nextMove)) {
							return reacheAblePoint(blocks, nextMove, exit,
									steps);
						}
					}
				}
			}
		}
		return true;
	}

	private boolean isValidMove(Block[][] blocks, int[] actual, int[] next) {
		int a = actual[0];
		int b = actual[1];
		int c = next[0];
		int d = next[1];
		// Going Up
		if (c == a + 1) {
			if (!blocks[a][b].isBlockedUp() && !blocks[c][d].isBlockedDown()) {
				return true;
			}
			// Going Down
		} else if (c == a - 1) {
			if (!blocks[a][b].isBlockedDown() && !blocks[c][d].isBlockedUp()) {
				return true;
			}
			// Going right
		} else if (d == b + 1) {
			if (!blocks[a][b].isBlockedRight() && !blocks[c][d].isBlockedLeft()) {
				return true;
			}
			// Going left
		} else if (d == b - 1) {
			if (!blocks[a][b].isBlockedLeft() && !blocks[c][d].isBlockedRight()) {
				return true;
			}
		}
		return false;
	}

	private boolean hasStep(List<int[]> steps, int[] step) {
		for (int i = 0; i < steps.size(); i++) {
			if (steps.get(i)[0] == step[0] && steps.get(i)[1] == step[1]) {
				return true;
			}
		}
		return false;
	}
}
