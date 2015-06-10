package database;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class MyQuery
{
	private Connection conn = null;
	private Statement stmnt = null;
	private ResultSet rs = null;
	Random rand = new Random();
	int MAX = 56;//the maximum number of questions in the table
	
	public MyQuery(Connection c)throws SQLException
	{
		conn = c;
		stmnt = conn.createStatement();
	}
	
	
	//Prints the entire 'questions' table in the database, including column headers
	public void printAllQuestions()throws SQLException
	{
		System.out.println();
		System.out.println("----------------QUESTIONS------------------");
		
		String query = "SELECT * FROM questions;";
		rs = stmnt.executeQuery(query);
		
		String qNum = "Num";
		String question = "Question";
		String qType = "Type";
		System.out.printf("%-5s %-200s %-20s\n", qNum, question, qType);
		
		while (rs.next())
		{
			qNum = rs.getString("QuestionNum");
			question = rs.getString("Question");
			qType = rs.getString("Type");
			System.out.printf("%-5s %-200s %-20s\n", qNum, question, qType);
		}
	}
	
	//Prints the entire 'answers' table in the database, including column headers
	public void printAllAnswers()throws SQLException
	{
		System.out.println();
		System.out.println("----------------ANSWERS------------------");
		
		String query = "SELECT * FROM answers;";
		rs = stmnt.executeQuery(query);
		
		String qNum = "QNum";
		String aNum = "ANum";
		String correct = "Correct";
		String answer = "Answer";
		System.out.printf("%-5s %-5s %-8s %-150s\n", qNum, aNum, correct, answer);
		
		while (rs.next())
		{
			qNum = rs.getString("QuestionNum");
			aNum = rs.getString("AnswerNum");
			correct = rs.getString("Correct");
			answer = rs.getString("Answer");
			System.out.printf("%-5s %-5s %-8s %-150s\n", qNum, aNum, correct, answer);
		}
	}
	
	//Do we want to not have any possibility of duplicating questions? I can do this but it requires a list
	//Check types of questions and create a question object that has all components, including answers, for easier display in game
	public Question getQuestion()throws SQLException
	{
		int newQNum = rand.nextInt(MAX)+1;
		//System.out.println("Question Number is: " + newQNum);
		
		//Get question corresponding with random number
		String query = "SELECT * FROM questions WHERE QuestionNum="+newQNum+";";
		rs = stmnt.executeQuery(query);
		
		//Print out Question
		//String qNum = "Num";
		String question = "Question";
		String qType = "Type";
		//System.out.printf("%-5s %-200s %-20s\n", qNum, question, qType);
		//qNum = rs.getString("QuestionNum");
		question = rs.getString("Question");
		qType = rs.getString("Type");
		//System.out.printf("%-5s %-200s %-20s\n", qNum, question, qType);
		
		//Get answers corresponding with question number
		query = "SELECT * FROM answers WHERE QuestionNum="+newQNum+";";
		rs = stmnt.executeQuery(query);
		
		//Print out Answers
		//qNum = "QNum";
		//String aNum = "ANum";
		String correct = "Correct";
		String answer = "Answer";
		//System.out.printf("%-5s %-5s %-8s %-150s\n", qNum, aNum, correct, answer);
		Question newQuestion = new Question(question, qType);//Create question object
		
		while (rs.next())
		{
			//qNum = rs.getString("QuestionNum");
			//aNum = rs.getString("AnswerNum");
			correct = rs.getString("Correct");
			answer = rs.getString("Answer");
			//System.out.printf("%-5s %-5s %-8s %-150s\n", qNum, aNum, correct, answer);
			newQuestion.addAnswer(answer, Integer.parseInt(correct));//Add current answer to the question object
		}
		
		return newQuestion;
		
	}
	
	//Asks the user if they wish to add a new question, and upon affirmative asks the user for question
	//specifics before entering it into the database.
	public void askQuestionInfo()throws SQLException
	{
		Scanner kb = new Scanner(System.in);
		String input = null;
		String type = null;
		
		System.out.println("Would you like to add a new question to the database?");
		input = kb.nextLine();
		
		if(!(input.toLowerCase().equals("y"))&&!(input.toLowerCase().equals("yes")))
		{
			kb.close();
			return;
		}
		
		while(!(input.toLowerCase().equals("exit")))//won't exit
		{
			int choice = 0;
			System.out.println("Please enter the question, text only, with your desired punction:");
			input = kb.nextLine();
			
			while((choice < 1)||(choice > 3))
			{
				System.out.println("Is the question 1: Multiple Choice, 2: True/False, or 3: Short Answer?");
				choice = kb.nextInt();
				kb.nextLine();
				if((choice < 1)||(choice > 3))
					System.out.println("Error: please type either '1', '2', or '3', then press enter.");
			}
			if(choice == 1)//Adding a multiple choice question
			{
				type = "MultipleChoice";
				addQuestion(input, type);
				
				String ansA, ansB, ansC, ansD;
				int a = 0, b = 0, c = 0, d = 0, corrAns = 0;
				System.out.println("There are four multiple choice answers that can be given, and only one may be correct.");
				System.out.println("They will be formatted within the database automatically.");
				System.out.println("Enter the first answer, text only:");
				ansA = kb.nextLine();
				ansA = "A. " + ansA;
				System.out.println("Enter the second answer, text only:");
				ansB = kb.nextLine();
				ansB = "B. " + ansB;
				System.out.println("Enter the third answer, text only:");
				ansC = kb.nextLine();
				ansC = "C. " + ansC;
				System.out.println("Enter the fourth answer, text only:");
				ansD = kb.nextLine();
				ansD = "D. " + ansD;
				while((corrAns < 1)||(corrAns > 4))
				{
					System.out.println("Which answer is the correct one? (1, 2, 3, or 4)");
					corrAns = kb.nextInt();
				}
				if(corrAns == 1)
					a = 1;
				else if(corrAns == 2)
					b = 1;
				else if(corrAns == 3)
					c = 1;
				else
					d = 1;
				addMCAnswers(ansA, a, ansB, b, ansC, c, ansD, d);
				
			}
			else if(choice == 2)//Adding a true/false question
			{
				type = "TrueFalse";
				input = "True/False: " + input;
				addQuestion(input, type);
				int correctTrue = 0, correctFalse = 0;
				
				System.out.println("Is this question true or false? t/f");
				input = kb.nextLine();
				if(input.toLowerCase().equals("t")||input.toLowerCase().equals("true"))
					correctTrue = 1;
				else
					correctFalse = 1;
				
				addTFAnswers(correctTrue, correctFalse);
			}
			else//adding a short answer question
			{
				type = "ShortAnswer";
				addQuestion(input, type);
				
				System.out.println("Enter the answer, text only, with your desired punctuation:");
				String answer = kb.nextLine();
				
				addSAnswer(answer);
			}
			System.out.println("Do you want to add another question to the database? (if no, type 'exit' to end)");
			input = kb.nextLine();
			kb.nextLine();
		}
		
		kb.close();
		
	}
	
	//Adds a new question into the database, along with it's specified type
	public void addQuestion(String newQuestion, String newQType)throws SQLException
	{
		String query = "SELECT MAX(QuestionNum) FROM questions;";
		rs = stmnt.executeQuery(query);
		
		//Increase the maximum number of questions by one and get the index for the new question
		int maxQNum = rs.getInt(1);
		maxQNum = maxQNum+1;
		MAX = maxQNum;
		System.out.println("New Question Number: " + maxQNum);
		
		//insert question
		//query = "INSERT INTO questions(QuestionNum,Question,Type) VALUES(" + maxQNum + ",'" + newQuestion + "','" + newQType + "');";
		query = "INSERT INTO questions(QuestionNum,Question,Type) VALUES("+maxQNum+",'"+newQuestion+"','"+newQType+"');";
		stmnt.executeUpdate(query);
		System.out.println("Inserted Question");
		
	}
	
	//Adds four multiple choice answers into the database, passed in as Strings, as well as an int for each answer indicating if it is correct
	public void addMCAnswers(String ansA, int aCorr, String ansB, int bCorr, String ansC, int cCorr, String ansD, int dCorr)throws SQLException
	{
		//Get the question number that the answers correspond to
		String query = "SELECT MAX(AnswerNum) FROM answers;";
		rs = stmnt.executeQuery(query);
		
		//get answer index
		int maxANum = rs.getInt(1);
		maxANum = maxANum+1;
		
		//insert answers
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+aCorr+",'"+ansA+"');";
		
		System.out.println(query);
		maxANum = maxANum+1;
		stmnt.executeUpdate(query);
		
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+bCorr+",'"+ansB+"');";
		
		System.out.println(query);
		maxANum = maxANum+1;
		stmnt.executeUpdate(query);
		
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+cCorr+",'"+ansC+"');";
		
		System.out.println(query);
		maxANum = maxANum+1;
		stmnt.executeUpdate(query);
		
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+dCorr+",'"+ansD+"');";
		
		System.out.println(query);
		stmnt.executeUpdate(query);
		
		System.out.println("Inserted Answers");
	}
	
	//Adds two true/false answers into the database, accepts two ints passed in to indicate which is the correct answer.
	public void addTFAnswers(int corrT, int corrF)throws SQLException
	{
		//Get the question number that the answers correspond to
		String query = "SELECT MAX(AnswerNum) FROM answers;";
		rs = stmnt.executeQuery(query);
		
		//get answer index
		int maxANum = rs.getInt(1);
		maxANum = maxANum+1;
		
		//insert answers
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+corrT+",'True');";
		
		maxANum = maxANum+1;
		stmnt.executeUpdate(query);
		
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+","+corrF+",'False');";
		
		stmnt.executeUpdate(query);
	}
	
	//Adds a single short-answer string into the database, accepting the string as a parameter that contains the answer
	public void addSAnswer(String ans)throws SQLException
	{
		//Get the question number that the answer corresponds to
		String query = "SELECT MAX(AnswerNum) FROM answers;";
		rs = stmnt.executeQuery(query);
		
		//get answer index
		int maxANum = rs.getInt(1);
		maxANum = maxANum+1;
		
		query = "INSERT INTO answers(QuestionNum,AnswerNum,Correct,Answer) VALUES("+MAX+","+maxANum+",1,'"+ans+"');";
		stmnt.executeUpdate(query);
	}
	
	
	
}
