//imports
import java.util.Scanner;
import java.util.Random;
import java.text.DecimalFormat;
//class
public class Main {
	//main
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
						}//end if
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
							//can call Dicegame method multiple times
							Dicegame();
							//break
							break;
							case 2: System.out.println("GTA is now playing.");
							//game method 2 go here
							//break;
							break;
							}//end switch
							
						}//end if
					}//end if
				//while x is less than 1 and canplay is equal to false
				}while(x<1&&canplay==false);
			}//end main
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
				}//end if
				//else statement
				else {
					//cannot play game
					System.out.println("Game is for mature audiences only!");
					//return false;
					return false;
				}//end else
			}//end checkifplaygame method
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
				//your bankaccount is between 1 and a hundred
				Double bankaccount =random.nextDouble(100)+1;
				System.out.println("Your bankaccount contains:"+df.format(bankaccount));
				//prompt user rolls
				System.out.println("Your rolls is as followed:");
				//does the code
				do {
				//dicegame1 is rolled 1-6
				int dicegame1 = random.nextInt(6)+1;
				//dicegame2 is rolled 1-6
				int dicegame2 = random.nextInt(6)+1;
				//rollnum is being incremented
				rollnum++;
				//prints out the dicegame rolls
				System.out.println(dicegame1);
				System.out.println(dicegame2);
				//if at least two of the dice rolled the same value, you don't have to pay, you won the game, and earnings is shown.
					if(dicegame1==dicegame2) {
					System.out.println("You do not have to pay!");
					System.out.println("You won the game!");
					System.out.println("You keep the earnings of: "+df.format(bankaccount));
					//roll is equal to false, to not roll again
					roll=false;
				}//end if
				//else if its different values, you have to pay
				else if(dicegame1!=dicegame2) {
					//randomsubtract is between 1 and 50
					Double randomsubtract= random.nextDouble(50)+1;
					//formulas to get the new bankaccount balance after each roll
					//you add the original bankaccount with the randomsubtract 
					Double bankaccount2=bankaccount+randomsubtract;
					//randomsubtract is being multiplied by two since we will have to subtract it anyways
					Double randomsubtractmultiply=randomsubtract*2;
					//you subtract both bankaccount2 and randomsubtractmultiply to get the newbankaccount balance each time
					Double newbankaccountbalance = bankaccount2-randomsubtractmultiply;
					//amount you have to pay
					System.out.println("You have to pay: "+df.format(randomsubtract));
					//what your bankaccount now contains
					System.out.println("Your bankaccount now contains: "+df.format(newbankaccountbalance));
					//roll is equal to true, to roll again
					roll=true;
					//if you loose all your money before you do not have to pay or if rollnum reaches 6 times, you loose the game
					//earnings is shown and rollnumber
					if(rollnum==6||bankaccount<0) {
						System.out.println("You lost the game!");
						System.out.println("Your earnings is: "+df.format(bankaccount)+" and you on roll number: "+rollnum);
						//roll is equal to false, to not roll again
						roll=false;
					}//end if
					}//end else if
				//while rollnum is less than 6 and roll is equal to true, keep rolling till you do not have to pay
				}while(rollnum<6&&roll==true);
				//THING TO FOCUS ON:
				//FIND OUT HOW YOUR BANKACCOUNT WILL CHANGE WHEN YOU ROLL THE DICE FOR THIS METHOD
				//FIND OUT THE OTHER MATURE GAME TO END UP PLAYING
				
			}//end Dicegame method

	}//end class


