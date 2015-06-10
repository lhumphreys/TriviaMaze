package Tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Game.GameSerializator;
import MazeGen.Maze;
import MazeGen.MazeGen;

public class GameSerializatorTest {

	@Test
	public void test() {

		String fileName = "test";
		String fakeFileName = "aTestToCheckForNonExistedSavedGamesSuchThatItMustReturnFalse";

		// Create maze object
		Maze maze = MazeGen.getInstance().generate(8, new int[] { 0, 0 },
				new int[] { 7, 7 });

		// Get Serializator object
		GameSerializator serializator = GameSerializator.getInstance();

		// Attempt to save maze
		try {
			serializator.saveMaze(maze, fileName);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		System.out.println("LISTING SAVED GAMES LIST\n");
		// Attempt to list the saved games
		String[] savedGames = serializator.getSavedMazesNames();
		for (String string : savedGames) {
			System.out.println(string);
		}

		System.out.println("\nPRINTING MAZE\n");

		// Check if saved maze exists
		assertTrue("Saved maze not found",
				serializator.savedGameExists(fileName));

		// Check for an arbitrary "unlikely to exist", maze name
		// This test may fail in case the user saved a maze with the same name
		// use for this test case
		assertFalse(
				"Fake saved game found. Possible program error or unexpected user input",
				serializator.savedGameExists(fakeFileName));

		// Attempt to load the saved maze
		Maze loadedMaze = null;
		try {
			loadedMaze = serializator.loadMaze(fileName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		loadedMaze.printMazeBlocksContraints();

		// Test maze original start point
		assertEquals(0, maze.getStart()[0]);
		assertEquals(0, maze.getStart()[1]);

		// Test maze original end point
		assertEquals(7, maze.getEnd()[0]);
		assertEquals(7, maze.getEnd()[1]);

		// Test the maze player position point
		assertEquals(0, maze.getStart()[0]);
		assertEquals(0, maze.getStart()[1]);

		// Test maze original size
		assertEquals(8, maze.getMazeSize());
	}
}
