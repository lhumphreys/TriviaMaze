/* Amber Wise
 * Version 0.0.1
 * 
 * This program allows the game to connect to the database holding the questions and answers for the trivia maze. It allows
 * the user to establish the connection to the database, then create a MyQuery object that provides several methods to access
 * or manipulate the data. The user can print all of the questions in the database, print all of the answers, generate a random
 * question with its corresponding answers, add a question, and add the answers to that question.
 * 
 */
import java.sql.*;

public class DatabaseAccess
{
	public static void main(String [] args)
	{
		try
		{
			Connection c = getConnection();
			MyQuery myQuery = new MyQuery(c);
			
			myQuery.printAllQuestions();
			myQuery.printAllAnswers();
			myQuery.getQuestion();
			System.out.println("Adding multiple choice question : What is my name?");
			myQuery.addQuestion("What is my name?", "Multiple Choice");
			myQuery.addMCAnswers("A. Amber", 1, "B. Jessica", 0, "C. Bobby", 0, "D. Fluffy", 0);
			System.out.println("Adding true/false question : I am a princess");
			myQuery.addQuestion("True/False: I am a princess.", "TrueFalse");
			myQuery.addTFAnswers(0, 1);
			System.out.println("Adding short-answer question : What is my last name?");
			myQuery.addQuestion("What is my last name?", "ShortAnswer");
			myQuery.addSAnswer("Wise");
			myQuery.askQuestionInfo();
			myQuery.printAllQuestions();
			myQuery.printAllAnswers();
			
			//rs.close();
			//stmnt.close();
			c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
	    }
		
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
}
