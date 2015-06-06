import java.util.ArrayList;

public class Question
{
	private String question = "";
	private String type = "";
	private int correctIndex;
	private ArrayList <String> answers = new ArrayList<String>();
	
	//Constructor
	public Question(String newQ, String newType)
	{
		this.question = newQ;
		this.type = newType;
	}
	
	//Adds a passed in string to the list of answers, and if it is correct assigns the index to that answer
	public void addAnswer(String newAns, int correct)
	{
		this.answers.add(newAns);
		if(correct == 1)
			this.correctIndex = (answers.size()-1);
	}
	
	//Gets
	public String getQuestion()
	{
		return this.question;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public ArrayList<String> getAnswers()
	{
		return this.answers;
	}
	
	public String getCorrect()
	{
		return this.answers.get(correctIndex);
	}
	
	//Prints the type of question and the question itself, both preceded by a blank line
	public void printQuestion()
	{
		System.out.println();
		System.out.println(this.type + ":");
		System.out.println("  " + this.question);
	}
	
	//Prints all answers pertaining to current question, followed by a blank line
	public void printAnswers()
	{
		for(int x = 0;x < this.answers.size(); x++)
		{
			System.out.println("     " + answers.get(x));
		}
		System.out.println();
	}
	
	//Checks if the passed-in String matches the correct answer
	public boolean isCorrect(String ans)
	{
		//For multiple choice, only the first character must match the correct answer
		if(this.type.equals("Multiple Choice"))
		{
			if(ans.toLowerCase().charAt(0) == this.answers.get(this.correctIndex).toLowerCase().charAt(0))
				return true;
			else
				return false;
		}//In true/false and short answer, the whole string must match the correct answer
		else if(ans.toLowerCase().equals(this.answers.get(correctIndex).toLowerCase()))
			return true;
		else//If it does not match the answer at the correct index, their answer is wrong
			return false;
	}
	
	public boolean equalTo(Question temp)
	{
		boolean equalTo = false;
		
		//Check if questions match
		if(this.question.equals(temp.getQuestion()))
		{
			//Check if types match
			if(this.type.equals(temp.getType()))
			{
				//Check if both have same correct answer
				if(this.getCorrect().equals(temp.getCorrect()))
				{
					//Check if all other possible answers are correct
					for(int x = 0; x < answers.size(); x++)
					{
						if(this.answers.get(x).equals(temp.getAnswers().get(x)))
						{
							equalTo = true;
						}
						else
						{
							return false;
						}
					}
				}
			}
				
		}
		return equalTo;
	}
	
}//end class