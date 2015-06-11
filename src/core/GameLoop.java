package core;

import java.util.*;
import java.io.IOException;
import java.sql.*;

import maze.Block;
import maze.Maze;
import maze.MazeGen;
import database.MyQuery;
import database.Question;

public class GameLoop {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Welcome to the Trivia Maze Game!");
		
		try
		{
			Connection c = getConnection();
			MyQuery myQuery = new MyQuery(c);
			Maze maze;
		
			int choice = mainMenu(kb);
			while(choice == -1)
			{
				choice = mainMenu(kb);
			}
			
			switch(choice)
			{
			case 1:
				int [] start = {0,0};
				int [] end = {3,3};
				MazeGen gen = MazeGen.getInstance();
				maze = gen.generate(5, start, end);
				loop(kb, maze, myQuery);
				break;
			case 2:
				maze = loadGame(kb);
				loop(kb, maze, myQuery);
				break;
			case 3:
				printAbout(kb);
				break;
			case 0:
				adminTool(kb, myQuery);
				break;
			}
		c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
	    }
	}
	
	static Maze loadGame(Scanner kb)
	{
		System.out.println("Please enter the name of your game save: ");
		System.out.println("(For testing, try 'Laura')");
		
		String gameName = kb.nextLine();
		GameSerializator gs = GameSerializator.getInstance();
		
		Maze maze = null;
		try {
			maze = gs.loadMaze(gameName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("That save game was not found.");
		}
		return maze;
	}
	
	static boolean askQuestion(Scanner kb, MyQuery myQuery)
	{
		boolean correct;
		Question question = null;
		
		try {
			question = myQuery.getQuestion();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		
		question.printQuestion();
		
		if(question.isMultipleChoice())
		{
			question.printAnswers();
		}
		
		String answer = kb.nextLine();
		
		correct = question.isCorrect(answer);
		
		return correct;
	}
	
	static void loop(Scanner kb, Maze maze, MyQuery myQuery)
	{
		boolean won = false;
		boolean lost = false;
		boolean quit = false;
		int choice;
		
		while(!won && !lost && !quit)
		{
			Block pos = maze.getCurrentBlock();
			pos.printRoom();
			choice = gameMenu(kb);
			
			switch(choice)
			{
			case 1:
				
				if(pos.isBlockedUp())
				{
					System.out.println("You can't go that way!");
				}
				else
				{
					if(askQuestion(kb, myQuery))
					{
						System.out.println("Correct!");
						maze.movePlayerUp();
						won = checkWon(maze);
						
					}
					else
					{
						System.out.println("Wrong answer!  The door is locked!");
						pos.blockUp();
						lost = checkLost(maze);
					}
				}
				break;
			case 2:
				
				if(pos.isBlockedDown())
				{
					System.out.println("You can't go that way!");
				}
				else
				{
					if(askQuestion(kb, myQuery))
					{
						System.out.println("Correct!");
						maze.movePlayerDown();
						won = checkWon(maze);
					}
					else
					{
						System.out.println("Wrong answer!  The door is locked!");
						pos.blockDown();
						lost = checkLost(maze);
					}
				}
				break;
			case 3:
				if(pos.isBlockedRight())
				{
					System.out.println("You can't go that way!");
				}
				else
				{
					if(askQuestion(kb, myQuery))
					{
						System.out.println("Correct!");
						maze.movePlayerRight();
						won = checkWon(maze);
					}
					else
					{
						System.out.println("Wrong answer!  The door is locked!");
						pos.blockRight();
						lost = checkLost(maze);
					}
				}
				break;
				
			case 4:
				
				if(pos.isBlockedLeft())
				{
					System.out.println("You can't go that way!");
				}
				else
				{
					if(askQuestion(kb, myQuery))
					{
						System.out.println("Correct!");
						maze.movePlayerLeft();
						won = checkWon(maze);
					}
					else
					{
						System.out.println("Wrong answer!  The door is locked!");
						pos.blockLeft();
						lost = checkLost(maze);
					}
				}
				break;
				
			case 0:
				System.out.println("Do you want to save before quitting?");
				System.out.println("[1] Yes");
				System.out.println("[2] No, just quit.");
				
				choice = kb.nextInt();
				kb.nextLine();
				
				if(choice == 1)
				{
					saveGame(kb, maze);
				}
				
				quit = true;
			}
		}
		
		if(won)
			System.out.println("Congratulations!  You won!");
		
		if(lost)
			System.out.println("There's nowhere else to move!  Too bad.");
	}
	
	static void saveGame(Scanner kb, Maze maze)
	{
		System.out.println("Please enter a name for your save game: ");
		String gameName = kb.nextLine();
		
		GameSerializator gs = GameSerializator.getInstance();
		
		try {
			gs.saveMaze(maze, gameName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Game saved!  Remember the name you entered to load your game later.");
	}
	
	static boolean checkWon(Maze maze)
	{
		int[] pos = maze.getPlayerPosition();
		int[] end = maze.getEnd();
		
		if(pos[0] == end[0] && pos[1] == end[1])
			return true;
		return false;
	}
	
	static boolean checkLost(Maze maze)
	{
		Block pos = maze.getCurrentBlock();
		
		if(pos.isBlockedUp() && pos.isBlockedDown() && pos.isBlockedRight()&& pos.isBlockedLeft())
			return true;
		return false;
	}
	
	static int mainMenu(Scanner kb)
	{
		int choice = -1;
		
		System.out.println("Options:");
		System.out.println("[1] New Game");
		System.out.println("[2] Load Game");
		System.out.println("[3] About");
		System.out.println("[0] Database Admin Tool");
		System.out.println("Choice: ");
		
		try{
		choice = kb.nextInt();
		kb.nextLine();
		
		if(choice != 1 && choice != 2 && choice != 3 && choice != 0)
		{
			System.out.println("Invalid menu choice.");
			choice = -1;
			System.out.println();
		}
		}
		catch(InputMismatchException e)
		{
			kb.nextLine();
			System.out.println("You must enter an integer.");
			System.out.println();
		}
		
		return choice;
	}
	
	static int gameMenu(Scanner kb)
	{
		int choice = -1;
		
		System.out.println("Which door would you like to try?");
		System.out.println("[1] North");
		System.out.println("[2] South");
		System.out.println("[3] East");
		System.out.println("[4] West");
		System.out.println();
		System.out.println("[0] Quit");
		System.out.println("Choice: ");
		
		
		try{
			choice = kb.nextInt();
			kb.nextLine();
			
			if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 0)
			{
				System.out.println("Invalid menu choice.");
				System.out.println();
			}
			}
			catch(InputMismatchException e)
			{
				System.out.println("You must enter an integer.");
				kb.nextLine();
				System.out.println();
			}
			
		
		return choice;
	}
	
	public static Connection getConnection()
	{
		Connection connection = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:triviagame.db");
			connection.setAutoCommit(false);//MAKES SURE DATABASE IS NOT ALTERED PERMANENTLY: CHANGE THIS BEFORE FINAL RELEASE
			System.out.println("Opened database successfully!");
		}
		catch (Exception e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		if(connection == null)
		{
			System.out.println("Error: connection is null!");
		}
		return connection;
	}
	
	public static void printAbout(Scanner kb)
	{
		System.out.println("TV Trivia Game");
		System.out.println();
		System.out.println("Written by THE ILLUMINATI");
		System.out.println();
		System.out.println("Laura Humphreys");
		System.out.println("Amber Wise");
		System.out.println("Augusto Melo");
		System.out.println();
		System.out.println("[0] Return");
		System.out.println("Choice: ");
		
		kb.nextLine();
	}
	
	public static void adminTool(Scanner kb, MyQuery myQuery)
	{
		int choice = -1;
		
		while(choice != 0)
		{
			choice = adminMenu(kb);
			try 
			{
				switch(choice)
				{
				case 1:
					
					myQuery.askQuestionInfo();
					break;
				case 2:
					myQuery.printAllQuestions();
					break;
				case 3:
					myQuery.printAllAnswers();
					break;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static int adminMenu(Scanner kb)
	{
		int choice = -1;
		
		System.out.println("[1] Add new question");
		System.out.println("[2] Print all questions");
		System.out.println("[3] Print all answers");
		System.out.println("[0] Cancel");
		System.out.println("Choice: ");
		
		try{
			choice = kb.nextInt();
			kb.nextLine();
			
			if(choice != 1 && choice != 2 && choice != 3 && choice != 0)
			{
				System.out.println("Invalid menu choice.");
				System.out.println();
			}
		}
		catch(InputMismatchException e)
		{
			System.out.println("You must enter an integer.");
			System.out.println();
		}
		
		return choice;
	}
}
