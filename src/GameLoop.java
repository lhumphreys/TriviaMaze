
import java.util.*;


public class GameLoop {

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		
		System.out.println("Welcome to the Trivia Maze Game!");
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
			Maze maze = gen.generate(4, start, end);
			loop(kb, maze);
			break;
		case 2:
			break;
		case 3:
			break;
		case 9:
			break;
		}
	}
	
	static boolean askQuestion()
	{
		return true;
	}
	
	static void loop(Scanner kb, Maze maze)
	{
		boolean won = false;
		boolean lost = false;
		int choice;
		
		while(!won && !lost)
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
					if(askQuestion())
					{
						maze.movePlayerUp();
						won = checkWon(maze);
						lost = checkLost(maze);
					}
					else
					{
						pos.blockUp();
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
					if(askQuestion())
					{
						maze.movePlayerDown();
						won = checkWon(maze);
						lost = checkLost(maze);
					}
					else
					{
						pos.blockDown();
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
					if(askQuestion())
					{
						maze.movePlayerRight();
						won = checkWon(maze);
						lost = checkLost(maze);
					}
					else
					{
						pos.blockRight();
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
					if(askQuestion())
					{
						maze.movePlayerLeft();
						won = checkWon(maze);
						lost = checkLost(maze);
					}
					else
					{
						pos.blockLeft();
					}
				}
				break;
				
			}
		}
		
		if(won)
			System.out.println("Congratulations!  You won!");
		
		if(lost)
			System.out.println("There's nowhere else to move!  Too bad.");
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
	
	static int gameMenu(Scanner kb)
	{
		int choice = -1;
		
		System.out.println("Which door would you like to try?");
		System.out.println("[1] North");
		System.out.println("[2] South");
		System.out.println("[3] East");
		System.out.println("[4] West");
		System.out.println();
		System.out.println("[0] Save and Quit");
		
		
		try{
			choice = kb.nextInt();
			
			if(choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 0)
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
