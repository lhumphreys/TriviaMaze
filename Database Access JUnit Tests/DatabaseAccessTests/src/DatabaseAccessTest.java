import static org.junit.Assert.*;

import java.sql.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


public class DatabaseAccessTest
{
	//Question test;
	Connection testConn = DatabaseAccess.getConnection();
	MyQuery testQuery;
	Question testQuestion;
	
	@Before
	public void setUp() throws Exception
	{
		//initial query
		testQuery = new MyQuery(testConn);
	}
	
	@After
	public void tearDown() throws Exception
	{
		testConn.close();
	}
	
	/*
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void shouldThrowSQLException() throws SQLException
	{
	    expectedEx.expect(SQLException.class);
	    MyQuery test = new MyQuery(testConn);
	}
	*/
	
	//Have printAllQuestions return a string instead of the format "qNum question qType\n"
	@Test
	public void testPrintAllQuestions() throws Exception
	{
		assertEquals(testQuery.printAllQuestions(), "1 In the anime \"Free!\", what do the four main characters of the Iwatobi Swim Club have in common? MultipleChoice\n"+ 
													"2 In the anime \"Ouran High School Host Club\", what are the names of the red-haired twins that are both hosts in the club? MultipleChoice\n"+
													"3 In the anime \"Death Note\", what is Light Yagami known as once mass amounts of criminals begin dying mysteriously as a result of him using the Death Note? MultipleChoice\n"+
													"4 In the anime \"Attack on Titan\", what are Eren Yeager and the rest of the Survey Corp taught to do in order to kill a titan? MultipleChoice\n"+
													"5 In the anime \"Parasyte: The Maxim\", why does the main character Shinichi Izumi not get completely taken over by the alien parasite trying to attack him? MultipleChoice\n"+
													"6 In the sitcom \"Seinfeld\", what was George Costanza known for doing when a kitchen caught fire at a child's birthday party? MultipleChoice\n"+
													"7 In the sitcom \"Big Bang Theory\", how was the elevator originally broken? MultipleChoice\n"+
													"8 In the sitcom \"Everybody Loves Raymond\", what is peculiar about Robert Barone's eating habits? MultipleChoice\n"+
													"9 In the sitcom \"How I Met Your Mother\", Barney Stinson is known for what? MultipleChoice\n"+
													"10 In the sitcom \"Fresh Prince of Bel-Air\", where was Will Smith's character originally from before he moved to Bel-Air? MultipleChoice\n"+
													"11 In the sci-fi show \"Supernatural\", what killed Sam and Dean's mother? MultipleChoice\n"+
													"12 In the sci-fi show \"Being Human\", why does Sally ignore her door, forfeiting her chance to move on to the afterlife? MultipleChoice\n"+
													"13 In the sci-fi show \"Marvel's Agents of S.H.I.E.L.D\", what is the name of the terrorist organization that resurfaces, despite it being believed to have been wiped out by Captain America in 1945? MultipleChoice\n"+
													"14 In the sci-fi show \"Doctor Who\", who is the actor that plays the Tenth Doctor? MultipleChoice\n"+
													"15 In the sci-fi show \"Land of the Lost\", what causes the father Rick and his two children, Will and Holly, to become trapped in the unknown world? MultipleChoice\n"+
													"16 Which popular 1990s TV series that ran from 1994-2004 featured characters named Rachel, Monica, Phoebe, Joey, Chandler, and Ross? MultipleChoice\n"+
													"17 Which cartoon television show based on the Griffin family became popular in the early 2000s? MultipleChoice\n"+
													"18 What were the names of the two government agents played by David Duchovny and Gillian Anderson in the 1993-2002 series X-Files? MultipleChoice\n"+
													"19 Which popular science fiction franchise aired its first episode in 1966? MultipleChoice\n"+
													"20 Who won season 1 of American Idol? MultipleChoice\n"+
													"21 What is the area of focus in CSI? MultipleChoice\n"+
													"22 Who was the director of Jackass The Movie? MultipleChoice\n"+
													"23 What show are these three characters from: DJ, Michelle and Joey? MultipleChoice\n"+
													"24 In the fantasy show \"Game of Thrones\", how many dragons does Daenerys own? MultipleChoice\n"+
													"25 In the fantasy show \"Game of Thrones\", what are Daenerys' dragons named? MultipleChoice\n"+
													"26 In the fantasy show \"Game of Thrones\", which character killed Joffrey? MultipleChoice\n"+
													"27 What does NCIS stand for? MultipleChoice\n"+
													"28 In the drama show \"Lost\", how many rafts did the survivors make during season 1? MultipleChoice\n"+
													"29 In the drama show \"Law & Order\", who is the captain of the Special Victims Unit? MultipleChoice\n"+
													"30 In the horror show \"The Walking Dead\", who is the first to die without being bitten? MultipleChoice\n"+
													"31 In the horror show \"The Walking Dead\", who thought Carol was a lesbian? MultipleChoice\n"+
													"32 In the fantasy show \"Once Upon a Time\", what was Snow White's mother's name? MultipleChoice\n"+
													"33 In the fantasy show \"Once Upon a Time\", what was a symbol of Rumple and Belle's love? MultipleChoice\n"+
													"34 In the anime \"Princess Tutu\", Princess Tutu's true identity is: MultipleChoice\n"+
													"35 In the anime \"Natsume's Book of Friends\", Natsume's Demonic companion spends his day-to-day hidden in what form? MultipleChoice\n"+
													"36 Which of these anime is NOT about a schoolgirl with the powers of a god? MultipleChoice\n"+
													"37 Which of these classic books has not been adapted into an anime? MultipleChoice\n"+
													"38 In the show \"Nashville\", what is Rayna's older sister's name? MultipleChoice\n"+
													"39 In the reality TV show \"RuPaul's Drag Race\", how does RuPaul tell contestants to leave the show? MultipleChoice\n"+
													"40 In the animated series \"Avatar: The Last Airbender\", what order does Aang master the elements in? MultipleChoice\n"+
													"41 In the TV sitcom \"Arrested Development\", how much money was hidden in the banana stand? MultipleChoice\n"+
													"42 In the anime \"Sailor Moon\", what is Sailor Moon's boyfriend's alter ego? ShortAnswer\n"+
													"43 In the show \"Twin Peaks\", Special Agent Dale Cooper says the Double R Diner's coffee is what? ShortAnswer\n"+
													"44 According to the animated reboot of \"My Little Pony\", friendship is what? ShortAnswer\n"+
													"45 In the show \"The Unbreakable Kimmy Schmidt\", Kimmy is called a what? ShortAnswer\n"+
													"46 From the comedy series \"It's Always Sunny in Philadelphia\", fill in the blank to the quote: \"Dayman! AAA-AAA-AAAAAH! Fighter of the ________! AAA-AAA-AAAAAH! Champion of the sun!\" ShortAnswer\n"+
													"47 From the comedy series \"30 Rock\", fill in the blank to the quote: \"________ ___ ______, spooky scary.  Boys becoming men.  Men becoming wolves.\" ShortAnswer\n"+
													"48 What is the word \"Digimon\" short for? ShortAnswer\n"+
													"49 What educational TV show's theme song features the lyrics \"BILL! BILL! BILL! BILL! BILL! BILL!\"? ShortAnswer\n"+
													"50 In the TV show \"The West Wing\", President Josiah Bartlet is diagnosed with cancer. TrueFalse\n"+
													"51  According to the animated series \"Spongebob Squarepants\", F is for Fires that burn down the whole town, U is for Uranium bombs, and N is for No survivors. TrueFalse\n"+
													"52 In the anime \"Kill La Kill\", Ryuko gets her super powers from a talking school uniform. TrueFalse\n"+
													"53 In the anime \"Fate/Stay Night\", King Arthur is a woman. TrueFalse\n"+
													"54 In the anime \"Neon Genesis Evangelion\", the heroes harness the power of friendship to defeat the final enemy. TrueFalse\n"+
													"55 In the show \"Bojack Horseman\", Bojack's rival's name is Mr. Peanutbutter. TrueFalse\n"+
													"56 In the comedy series \"Parks and Recreation\", the most famous celebrity in Pawnee, Indiana is a miniature horse named Li'l Sebastian. TrueFalse\n");
	}
	
	//Have printAllAnswers return a string instead of the format "qNum aNum correct answer\n"
	@Test
	public void testPrintAllAnswers() throws Exception
	{
		assertEquals(testQuery.printAllAnswers(), "1 1 0 A. They are all geniuses\n"+
												  "1 2 1 B. They all have feminine names\n"+
												  "1 3 0 C. They were all born within the same month\n"+
												  "1 4 0 D. They all have the same hair color\n"+
												  "2 5 1 A. Kaoru and Hikaru\n"+
												  "2 6 0 B. Haruhi and Mitsukuni\n"+
												  "2 7 0 C. Kyoya and Renge\n"+
												  "2 8 0 D. Tamaki and Takashi\n"+
												  "3 9 0 A. God\n"+
												  "3 10 0 B. Ryuk\n"+
												  "3 11 1 C. Kira\n"+
												  "3 12 0 D. Shinigami\n"+
												  "4 13 0 A. Hit the weak spot on the titan's stomach\n"+
												  "4 14 1 B. Slice the weak spot on the back of the titan's neck\n"+
												  "4 15 0 C. Stab the titan directly between the eyes\n"+
												  "4 16 0 D. Get eaten by the titan\n"+
												  "5 17 0 A. Shinichi wakes up and fights off the alien\n"+
												  "5 18 0 B. The alien is wounded and therefore not strong enough\n"+
												  "5 19 0 C. Shinichi is immune to the alien's effects\n"+
												  "5 20 1 D. Shinichi was wearing headphones when attacked\n"+
												  "6 21 0 A. Making sure the children got out safely\n"+
												  "6 22 0 B. Putting out the fire with an expensive scarf\n"+
												  "6 23 0 C. Pouring alcohol on the fire and accidentally making it worse\n"+
												  "6 24 1 D. Yelling and pushing the children out of the way to save himself\n"+
												  "7 25 1 A. An explosive container of rocket fuel\n"+
												  "7 26 0 B. Sheldon causing the power to go out\n"+
												  "7 27 0 C. Leonard hitting the button too hard\n"+
												  "7 28 0 D. It is still unknown why the elevator is out of order\n"+
												  "8 29 0 A. He only eats Tacos on Tuesdays\n"+
												  "8 30 0 B. He prays to his dead dog before every meal\n"+
												  "8 31 1 C. He touches the food to his chin before every bite\n"+
												  "8 32 0 D. He is the only vegetarian in the family\n"+
												  "9 33 0 A. Being extremely tall\n"+
												  "9 34 1 B. Being a womanizer\n"+
												  "9 35 0 C. Being excessively polite\n"+
												  "9 36 0 D. Having the largest hands\n"+
												  "10 37 0 A. East Philippines\n"+
												  "10 38 0 B. North Philipsburg\n"+
												  "10 39 1 C. West Philadelphia\n"+
												  "10 40 0 D. South Philmont\n"+
												  "11 41 0 A. A red-tailed Gryphon\n"+
												  "11 42 0 B. A blue-skinned Wendigo\n"+
												  "11 43 1 C. A yellow-eyed Demon\n"+
												  "11 44 0 D. A blonde-haired Leviathan\n"+
												  "12 45 0 A. Sally does not know how to open doors as a ghost and could not touch it\n"+
												  "12 46 1 B. Aiden was stabbed by a wooden stake and in critical condition, causing her to stay and make sure he survived\n"+
												  "12 47 0 C. Josh began to transform at the full moon, and she possessed him in order to save innocent bystanders\n"+
												  "12 48 0 D. Another ghost went through the door thinking it was theirs, and stole her moment to pass on\n"+
												  "13 49 0 A. Schutzstaffel\n"+
												  "13 50 0 B. Isis\n"+
												  "13 51 1 C. Hydra\n"+
												  "13 52 0 D. Deathlok\n"+
												  "14 53 0 A. Christopher Eccleston\n"+
												  "14 54 1 B. David Tennant\n"+
												  "14 55 0 C. Sylvester McCoy\n"+
												  "14 56 0 D. Matt Smith\n"+
												  "15 57 0 A. The sky opens up on a camping trip and sucks their tent into an alternate timeline\n"+
												  "15 58 0 B. A scientist places a portal in their house, using the family as an experiment to test inter-dimensional travel\n"+
												  "15 59 0 C. Rick was experimenting with the theory of time travel and accidentally brought them into a place where all timelines of the world mix together\n"+
												  "15 60 1 D. The family was rafting when an earthquake caused the ground to open up, landing them in the unfamiliar world\n"+
												  "16 61 1 A. Friends\n"+
												  "16 62 0 B. Enemies\n"+
												  "16 63 0 C. Acquaintances\n"+
												  "16 64 0 D. Everybody Still Talks About It, Let's Just Get Over It Please\n"+
												  "17 65 1 A. Family Guy\n"+
												  "17 66 0 B. Family Man\n"+
												  "17 67 0 C. Family Guys\n"+
												  "17 68 0 D. My Beloved Family\n"+
												  "18 69 0 A. Fox Murderer and Dana Scully\n"+
												  "18 70 0 B. Fox Mulder and Dana Skull\n"+
												  "18 71 0 C. Fox Muld and Diana Scully\n"+
												  "18 72 1 D. Fox Mulder and Dana Scully\n"+
												  "19 73 0 A. Start Trek\n"+
												  "19 74 0 B. Star Wars\n"+
												  "19 75 0 C. Dr. What\n"+
												  "19 76 1 D. Lost in Space\n"+
												  "20 77 1 A. Kelly Clarkson\n"+
												  "20 78 0 B. Kelly Morrison\n"+
												  "20 79 0 C. Kelly Kee\n"+
												  "20 80 0 D. Kelly Taylor\n"+
												  "21 81 1 A. Crime\n"+
												  "21 82 0 B. Chemistry\n"+
												  "21 83 0 C. Medicine\n"+
												  "21 84 0 D. Natural Disasters\n"+
												  "22 85 1 A. Jeff Tremaine\n"+
												  "22 86 0 B. Joffrey Tremannie\n"+
												  "22 87 0 C. George Tremanner\n"+
												  "22 88 0 D. Ithastobea Forest\n"+
												  "23 89 1 A. Full House\n"+
												  "23 90 0 B. Empty House\n"+
												  "23 91 0 C. Full Cabinet\n"+
												  "23 92 0 D. Little House on The Praire\n"+
												  "24 93 1 A. Three\n"+
												  "24 94 0 B. Four\n"+
												  "24 95 0 C. Five\n"+
												  "24 96 0 D. Over Nine Thousand\n"+
												  "25 97 1 A. Drogon, Viserion, and Rhaegal\n"+
												  "25 98 0 B. Drogon, Viesh, and Rhaeg\n"+
												  "25 99 0 C. Drogon, Visrion, and Rhomnir\n"+
												  "25 100 0 D. Drogal, Viesrion, and Rhaegal\n"+
												  "26 101 0 A. Olema\n"+
												  "26 102 0 B. Olaf\n"+
												  "26 103 0 C. Omar\n"+
												  "26 104 1 D. Olenna\n"+
												  "27 105 0 A. Nationalistic Criminal Investigative Service\n"+
												  "27 106 0 B. National Criminal Inquiry Service\n"+
												  "27 107 1 C. Naval Criminal Investigative Service\n"+
												  "27 108 0 D. National Criminal Investigatigation of Supects\n"+
												  "28 109 1 A. Two\n"+
												  "28 110 0 B. One\n"+
												  "28 111 0 C. Three\n"+
												  "28 112 0 D. Six\n"+
												  "29 113 0 A. Captain Noris\n"+
												  "29 114 0 B. Captain Cragen\n"+
												  "29 115 0 C. Captain Craig\n"+
												  "29 116 0 D. Captain Cracken\n"+
												  "30 117 1 A. Jacqui\n"+
												  "30 118 0 B. Mohamed\n"+
												  "30 119 0 C. Lori\n"+
												  "30 120 0 D. Rick\n"+
												  "31 121 0 A. Tyreese\n"+
												  "31 122 0 B. Sam\n"+
												  "31 123 1 C. Axel\n"+
												  "31 124 0 D. Carl\n"+
												  "32 125 1 A. Eva\n"+
												  "32 126 0 B. Chris\n"+
												  "32 127 0 C. Nora\n"+
												  "32 128 0 D. Natalie\n"+
												  "33 129 1 A. The chipped cup\n"+
												  "33 130 0 B. The cracked mug\n"+
												  "33 131 0 C. Rumple's magic amulet\n"+
												  "33 132 0 D. His heart in a box\n"+
												  "34 133 0 A. A pop idol\n"+
												  "34 134 0 B. A high school student\n"+
												  "34 135 0 C. A florist\n"+
												  "34 136 1 D. A duck\n"+
												  "35 137 0 A. A Labrador Retriever\n"+
												  "35 138 1 B. A porcelain cat\n"+
												  "35 139 0 C. A teddy bear\n"+
												  "35 140 0 D. A sentient notebook\n"+
												  "36 141 0 A. Kamisama Hajimemashita\n"+
												  "36 142 1 B. Chihayafuru\n"+
												  "36 143 0 C. Kamichu\n"+
												  "36 144 0 D. Sasami-san@Ganbaranai\n"+
												  "37 145 1 A. Lord of the Flies\n"+
												  "37 146 0 B. Anne of Green Gables\n"+
												  "37 147 0 C. The Count of Monte Cristo\n"+
												  "37 148 0 D. Romeo and Juliet \n"+
												  "38 149 0 A. Patricia\n"+
												  "38 150 0 B. Caramel\n"+
												  "38 151 0 C. Snowy\n"+
												  "38 152 1 D. Tandy\n"+
												  "39 153 0 A. \"Take a hike!\"\n"+
												  "39 154 0 B. \"Farewell for now\"\n"+
												  "39 155 1 C. \"Sashay away\"\n"+
												  "39 156 0 D. \"You've worked your last runway\"\n"+
												  "40 157 0 A. Air, Fire, Earth, Water\n"+
												  "40 158 1 B. Air, Water, Earth, Fire\n"+
												  "40 159 0 C. Earth, Water, Fire, Air\n"+
												  "40 160 0 D. Pizza, Beer, Homework, Sleep\n"+
												  "41 161 0 A. $100\n"+
												  "41 162 0 B. $400,000\n"+
												  "41 163 0 C. $1,500,000\n"+
												  "41 164 1 D. $250,000\n"+
												  "42 165 1 Tuxedo Mask\n"+
												  "43 166 1 Damn Fine\n"+
												  "44 167 1 Magic\n"+
												  "45 168 1 Mole Woman\n"+
												  "46 169 1 Nightman\n"+
												  "47 170 1 Werewolf Bar Mitzvah\n"+
												  "48 171 1 Digital Monsters\n"+
												  "49 172 1 Bill Nye the Science Guy\n"+
												  "50 173 0 True\n"+
												  "50 174 1 False\n"+
												  "51 175 0 True\n"+
												  "51 176 1 False\n"+
												  "52 177 1 True\n"+
												  "52 178 0 False\n"+
												  "53 179 1 True\n"+
												  "53 180 0 False\n"+
												  "54 181 0 True\n"+
												  "54 182 1 False\n"+
												  "55 183 1 True\n"+
												  "55 184 0 False\n"+
												  "56 185 1 True\n"+
												  "56 186 0 False\n");
	}
	
	@Test
	public void testGetQuestion() throws Exception
	{
		Question testQuestion = new Question("In the anime \"Free!\", what do the four main characters of the Iwatobi Swim Club have in common?", "MultipleChoice");
		testQuestion.addAnswer("A. They are all geniuses", 0);
		testQuestion.addAnswer("B. They all have feminine names", 1);
		testQuestion.addAnswer("C. They were all born within the same month", 0);
		testQuestion.addAnswer("D. They all have the same hair color", 0);
		assertEquals(true, testQuestion.equalTo(testQuery.getQuestion(1)));
		//assertEquals(testQuestion, testQuery.getQuestion(1));
	}
	
	@Test
	public void testAddQuestionInfo() throws Exception
	{
		//Create ShortAnswer question
		Question testQOne = new Question("What is my name?", "ShortAnswer");
		testQOne.addAnswer("Amber", 1);
		//Insert an identical question
		testQuery.addQuestion("What is my name?", "ShortAnswer");
		testQuery.addSAnswer("Amber");
		//Pull last question from database and see if they match
		assertEquals(true, testQOne.equalTo(testQuery.getQuestion(MyQuery.getMAX())));
		
		//Create MultipleChoice question
		Question testQTwo = new Question("When is my birthday?", "MultipleChoice");
		testQTwo.addAnswer("A. December", 1);
		testQTwo.addAnswer("B. November", 0);
		testQTwo.addAnswer("C. October", 0);
		testQTwo.addAnswer("D. September", 0);
		//Insert an identical question
		testQuery.addQuestion("When is my birthday?", "MultipleChoice");
		testQuery.addMCAnswers("A. December", 1, "B. November", 0, "C. October", 0 , "D. September", 0);
		//Pull last question from database and see if they match
		assertEquals(true, testQTwo.equalTo(testQuery.getQuestion(MyQuery.getMAX())));
		
		//Create True/False question
		Question testQThree = new Question("I only have one foot.", "TrueFalse");
		testQThree.addAnswer("True", 0);
		testQThree.addAnswer("False", 1);
		//Insert an identical question
		testQuery.addQuestion("I only have one foot.", "TrueFalse");
		testQuery.addTFAnswers(0, 1);
		//Pull last question from database and see if they match
		assertEquals(true, testQThree.equalTo(testQuery.getQuestion(MyQuery.getMAX())));
	}	
	
}