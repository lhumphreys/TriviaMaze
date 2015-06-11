/*
 * Team Name: The Illuminati
 * Author: Augusto Melo
 * Date Modified: 6/10/2015
 * CSCD 350
 * 
 * This class serializes and deserializes the maze
 * for the save game functionality.
 * 
 */

package core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import maze.Maze;



public class GameSerializator 
{
	private static GameSerializator INSTANCE;
	private static final String SAVED_FOLDER_NAME = "saved";
	private static final String SAVED_GAME_EXTENTION = ".tv";

	public static GameSerializator getInstance() 
	{
		if (INSTANCE == null) 
		{
			INSTANCE = new GameSerializator();
		}
		File file = new File(SAVED_FOLDER_NAME);
		if (!file.exists()) 
		{
			file.mkdir();
		} 
		else 
		{
			if (!file.isDirectory()) 
			{
				file.mkdir();
			}
		}
		return INSTANCE;
	}

	private GameSerializator() 
	{
		// Singleton
	}

	// The savedGameName parameter cannot include the file extension
	public Maze loadMaze(String savedGameName) throws IOException,
			ClassNotFoundException 
	{

		if (!savedGameExists(savedGameName)) 
		{
			throw new FileNotFoundException("Saved game does not exist");
		}
		File file = new File(SAVED_FOLDER_NAME + "/" + savedGameName
				+ SAVED_GAME_EXTENTION);

		FileInputStream fileInputStream = new FileInputStream(file);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				fileInputStream);

		Maze maze = (Maze) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();
		return maze;
	}

	// The gameName parameter cannot include the file extension
	public void saveMaze(Maze maze, String gameName) throws IOException 
	{
		File file = new File(SAVED_FOLDER_NAME + "/" + gameName
				+ SAVED_GAME_EXTENTION);

		if (!savedGameExists(gameName)) 
		{
			file.createNewFile();
		}

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(
				fileOutputStream);
		objectOutputStream.writeObject(maze);
		fileOutputStream.close();
		objectOutputStream.close();
	}

	// The gameName parameter cannot include the file extension
	public boolean savedGameExists(String gameName) 
	{
		File file = new File(SAVED_FOLDER_NAME + "/" + gameName
				+ SAVED_GAME_EXTENTION);
		if (!file.exists()) 
		{
			return false;
		}

		return true;
	}

	// Simply return a list of saved game names (including file extension) to be
	// displayed in the load game process
	public String[] getSavedMazesNames() 
	{
		File file = new File(SAVED_FOLDER_NAME);
		return file.list();
	}
}
