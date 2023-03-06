import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
public class Main {

	public static void main(String[] args) {
				//Method = a block of code that is executed whenever it is called upon
				//arguments to pass values in method
				//matching set of arguments and parameters
				//parameters don't have to be consistent
				//return types
				//method call to print name
				Hello();
				//set x to 1
				int x = 1;
				//set boolean can play to false
				boolean canplay=false;
				//do the code
				do {
					//if x is equal to one
					if(x==1) {
						//can play method is being called
						canplay=checkifplaygame(x);
						//if canplay is equal to false, prompt user to play a different game.
						if(canplay==false) {
							System.out.println("Go find a different game, seriously.");
							//breaks user from seeing the game code
							break;
						}
						//if canplay is equal to true, prompt user to play the game.
						if(canplay==true) {
							System.out.println("Let's begin the game!");
							//game code, if 1 is rolled you play a dicegame game, if 2 is rolled you play ____ game
							//declare random class
							Random random = new Random();
							//int decision is between 1 and 2, depends on which number is said, a different game will be played
							int decision = random.nextInt(2)+1;
							//switch decision, each game will call a method to work
							switch(decision) {
							case 1: System.out.println("You are now gambling with dice:");
							//game method 1 go here
							Dicegame();
							break;
							case 2: System.out.println("GTA is now playing.");
							//game method 2 go here
							//break;
							break;
							}
							
						}
					}
				//while x is less than 1 and canplay is equal to false
				}while(x<1&&canplay==false);
			}
			//hello method
			public static void Hello() {
				//declare scanner
				Scanner scanner = new Scanner(System.in);
				//user instructions
				System.out.println("What is your name?");
				//Get name from user
				String name = scanner.nextLine();
				//Greets user and tells name
				System.out.println("Hello "+name+".");
			}
			//checkifplaygame method
			public static boolean checkifplaygame(int age) {
 				//declare scanner
				Scanner scanner = new Scanner(System.in);
				//user instructions
				System.out.println("What is your age?");
				//Get age from user
				int a = scanner.nextInt();
				//tells user age
				System.out.println("You are "+a+".");
				//if age is greater than or equal to 18
				if(a>=18) {
					//allowed to play game
					System.out.println("You can play the game!");
					//return true
					return true;
				}
				//else statement
				else {
					//cannot play game
					System.out.println("Game is for mature audiences only!");
					//return false;
					return false;
				}
			}
			//Dicegame method
			public static void Dicegame() {
				//int roll as 0
				int rollnum = 0;
				//boolean 
				boolean roll =true;
				//declare decimal format
				DecimalFormat df = new DecimalFormat("##.##");
				//declare random class
				Random random = new Random();
				//prompt user rolls
				System.out.println("Your rolls is as followed:");
				//does the code
				do {
				//dicegame is rolled 1-6
				int dicegame = random.nextInt(6)+1;
				//rollnum is being incremented
				rollnum++;
				//prints out the dicegame rolls
				System.out.println(dicegame);
				//if at least two of the dice rolled the same value, you don't have to pay
				//if(dicegame==1&&dicegame==1||dicegame==2&&dicegame==2||dicegame==3&&dicegame==3||dicegame==4&&dicegame==4||dicegame==5&&dicegame==5||dicegame==6&&dicegame==6) {
					if(dicegame==dicegame) {
					System.out.println("You do not have to pay!");
					//roll=false, to not roll again
					//roll=true;
				}
				//else if its different values, you have to pay
				else if(dicegame!=dicegame) {
					System.out.println("You have to pay!");
					//roll is equal to false, to not roll again
					roll=false;
					}
				//rollnum is less than 6 and roll is equal to true
				}while(rollnum<6&&roll==true);
				//your bankaccount is between 1 and a hundred
				//find out how your bankaccount will change when you roll the dice for this method
				Double bankaccount =random.nextDouble(100)+1;
				System.out.println("Your bankaccount contains:"+df.format(bankaccount));
			}

	}
