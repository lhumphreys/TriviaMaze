import java.sql.*;

public class SQLiteJDBC
{
	public static void main( String args[] )
	  {
	    Connection c = null;
	    Statement stmnt = null;
	    try
	    {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:triviagame.db");
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");
	      
	      stmnt = c.createStatement();
	      ResultSet rs = stmnt.executeQuery("SELECT * FROM questions;");
	      
	      //System.out.printf("%-7s %-100s %-20 \n", "Number", "Question", "Type");
	      String num = "Number";
	      String q = "Question";
	      String t = "Type";
	      System.out.printf("%-7s %-200s %-20s\n", num, q, t);
	      
	      while (rs.next())
	      {
	         String qNum = rs.getString("QuestionNum");
	         String  question = rs.getString("Question");
	         String  qType = rs.getString("Type");
	         System.out.printf("%-7s %-200s %-20s\n", qNum, question, qType);
	         //System.out.println();
	      }
	      rs.close();
	      stmnt.close();
	      c.close();
	      
	    }
	    catch ( Exception e )
	    {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	  }
}
