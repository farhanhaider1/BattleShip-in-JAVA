/**
 * @author Farhan Haider
 * @Project BattleShip project
 * @Summary: 
 *       A player will place 5 of their ships on a 10 by 10 grid. The computer player
 *       will deploy five ships on the same grid. Once the game starts the player and 
 *       computer take turns, trying to sink each other's ships by guessing the coordinates
 *       to "attack". The game ends when either the player or computer has no ships left.
 */

import java.util.Random; //used to generate
import java.util.Scanner;

public class battleGame {

	public static void main(String[] args) {
		
		Scanner input = new Scanner (System.in); //open scanner
		Integer [][] gameArray = new Integer [10][10];
		System.out.println("\t\tWelcome the the battle ship game! \n"); //print welcome statement once
		fillArray(gameArray); //fill the array with 3's initially
		oceanMap(gameArray); //print the 2d array but replace 3 with a " "
		deployUserShip(gameArray,input); //ask user where to deploy user ships and change index from 3 to 1
		deployComputerShip(gameArray); //deploy computer ships only at empty slots and inform user when done
		gamePlay(gameArray, input); //call playerTurn and compTurn method so that both take turns
		
       input.close(); //close scanner
	} //main ends here
	
	/* METHOD: oceanMap
	 * 
	 * Input: String [][] array:
	 *            It takes a two dimensional array as an parameter
	 * Process:
	 *       The method will create an ocean map by looping over the array
	 *       and adding the indexes and pipe characters before and after each row.
	 *       It will use a nested for loop to print out the desired two dimensional image.
	 *       If the index of the array has 1 stored it will print a space on the map
	 *       If index is null or has 2 than it will hide the index by printing a space
	 *       Some formatting is done to display game in the center
	 * OUTPUT:
	 *      display the 2d array surrounded by indexes so that user can choose where to
	 *      deploy the ship.
	 *      
	 */
	public static void oceanMap(Integer [][] array) {
		
		
		System.out.println("\t\t  0 1 2 3 4 5 6 7 8 9"); //print column number
		
		//using nested loop to print array along with pipelines
		for(int i = 0; i <  array.length; i++) {
			
			System.out.print("\t\t"+i + "|"); ////prints the first pipeline and row number
			
			for(int j=0; j< array[i].length; j++) {
				
				if(array[i][j]== 2 || array[i][j]== 3) //initially every index has a 3
					System.out.print("  "); //prints the array itself	
				
				else if(array[i][j] == 4) System.out.print("# "); //computer ships stored as 4
				
				else if(array[i][j] == 0) System.out.print("! "); //if a opponent ship sinks
				
				else if(array[i][j] == 5) System.out.print("x "); //if sinks own ship
				
				else if(array[i][j] == 6) System.out.print("- "); //if sinks own ship
				
				else
					System.out.print("@ "); //@ presents user ship Stored as 1
				
			}//nested loop ends here
			
			System.out.println("| "+ i); //prints the final pipeline and row number
			System.out.println("");	
		}
		System.out.println("\t\t  0 1 2 3 4 5 6 7 8 9");	
	}//oceanMap method ends here...
	
	/*METHOD fillArray
	 * Input: String [][] array 
	 *            2d array from main
	 * Process: Go over each index and fill it with int 3	
	 * 
	 * Output: NONE
	 */
	public static void fillArray(Integer [][] array) {
		for(int i = 0; i < array.length; i++) {
			for(int j=0; j< array[i].length; j++){
				array[i][j] = 3;
			}
		} //for loop ends here
	}//fillArray ends here...*/
	
	/*METHOD deployUserShip
	 * INPUT: Integer[] [] Array
	 *                2d array from main
	 *        Scanner in:
	 *                To get user response
	 * 
	 * PROCESS: Prompt the user to deploy 5 ships one at a time. 
	 *         Every time use if statements to make sure that there 
	 *         are no ships already there and the coordinates are
	 *         within the ocean limits
	 *         At the end call the oceanMap method
	 *output:
	 *     Print 5 ships on the ocean map 
	 * 
	 */
	public static void deployUserShip(Integer[] [] Array, Scanner in) {	
		 //open Scanner to get coordinates
		System.out.println("You will be asked to deploy 5 ships");
		int playerShip = 5; //number of player ships
		int x = 0;
		int y = 0;
		for(int s = 0; s < playerShip; s++) {
			System.out.println("Deploy Ship " + (s+1));
			System.out.print("Give X Coordinates: ");
			x = in.nextInt();
			System.out.print("Give Y Coordinates: ");
			y = in.nextInt();
			
			//if statements
			
			if((x < 0 || x > 9) || (y < 0 || y > 9)) {
				System.err.println("Cant print outside battleZone");
				s--; //to decrease the s value for the loop to run
			}
			else if((x >= 0 && x < 10) && (y >= 0 && y < 10) && Array[x][y] == 1) {
                System.err.println("You can't place two or more ships on the same location");
                s--;
			}
			else if(Array[x][y] == 6) {
				System.out.println("A ship already exists there!  TRY AGAIN! ");
				s--;
			}
			   
			else {
				Array[x][y]= 1; //user ships saved as a 1 in array
			}
		}//for loop ends here
		System.out.println(" \t\tUser ships deployed ");
		oceanMap(Array);
	}//deployUserShip ends here...
	
	/*Method deployComputerShip
	 * Input:Integer[] [] Array: 2 dimensional array from main
	 * 
	 * Process: Randomly place ships for the computer such that
	 *          a hip is not already at the index
	 * output:
	 *          Inform user that computer is placing its ships 
	 *          Print the ocean map finally
	 */
	
	public static void deployComputerShip(Integer[] [] Array) {
		Random rand = new Random();
		int ships = 5; // 5 ships at the beginning of the game
		
		for(int s = 0; s < ships; s++) {
			int x = rand.nextInt(10); //GENERATE RANDOM NUMBERS BETWEEN 0 AND 9
			int y = rand.nextInt(10);
			
			if(Array[x][y] == 1) { //to make sure two ships are not placed on one index
				s--;
			}
			else if(Array[x][y] == 4) { //to make sure one ship is not placed twice
				s--;
			}
			else {
				System.out.println(" Computer deploying ship " + (s+1));
			    Array[x][y]= 4;
			}	
		}
		
		System.out.println(" Deployed "); //confirmation that code worked
		oceanMap(Array); //display outcome
		
	}//deployComputerShip ends here..
	
	/* Method playerTurn
	 * input: Integer[][] Arr:
	 *            2d array that has deployed ships
	 *        Scanner in:
	 *             Scanner to get user response
	 *Process:
	 *       Ask user for coordinates x and y to try and sink the ship
	 *       Check and see if there is a ship on the index
	 *       If a ship is available then check if it is a user ship or computer ship
	 *       If player or computer already searched the index then ask user for new input
	 *       Show messages after execution
	 * Output:
	 *       If there is a ship we replace it with a !
	 *       If the ship was user own ship then we replace it with a x
	 *       If no ship was deployed than missed message is showed and ocean map
	 *       will show - at the spot
	 *      
	 * 
	 */
	public static int playerTurn(Integer[][] Arr, Scanner in) {
		int numShip = 0;
		System.out.print(" GUESS COORDINATES OF OPPONETS SHIP \n X = ");
		int x = in.nextInt();
		System.out.print(" Y = ");
		int y = in.nextInt();
		
		//if else statements to decide outcome
		if((x < 0 || x > 9) || (y < 0 || y > 9)) {
			System.err.println("Cant print outside battleZone \n TRY AGAIN ");
			oceanMap(Arr); //print the ocean maP again
			playerTurn(Arr,in); //call same method again
			
		}
			
		else if(Arr[x][y] == 4 ) { //if computer had deployed a ship
			 Arr[x][y] = 0;
			 numShip = 9; //used later to count number of active ships
			 System.out.println("\t\t ***Boom! You sunk the ship! ***");
		 }
		 else if(Arr[x][y] == 1) { //if user himself has deployed a ship
			 Arr[x][y] = 5;
			 numShip = 8; //used later to count number of active ships
			 System.out.println("\t\t ***OPPS! You sunk your own ship!***");
		 }
		 else if(Arr[x][y] == 6) {
				System.err.println(" Already checked the spot.  TRY AGAIN ");
				 //print the ocean map again
				playerTurn(Arr,in); //use recursion to call the method again
		 }
		 else { //if no ship was there
			 Arr[x][y] = 6;
			 System.out.println("\t\t ***Sorry, you missed***");	 
		 }	 
		oceanMap(Arr);
		return numShip; //return outcome to track active ships
	}//playerTurn ends here...
	
	/*Method playerTurn
	 * input: Integer[][] Arr:
	 *            2d array that has deployed ships
	 *Process:
	 *       Randomly generate integers between 0 to 9 and use them to 
	 *       attack user ship.
	 *       Check if there is a ship at index or not
	 *       If there is a ship check whose ship is it
	 * Output:
	 *       If there is a user ship we replace it with a x
	 *       If the ship was computer ship then we replace it with a !
	 *       If no ship was deployed than missed message is showed and array shows -
	 * 
	 */
	
	public static int compTurn(Integer[][] twoD) {
		Random rand = new Random(); //to generate random numbers
		int EndShipNum = 0;
		System.out.println("Computer deploying ship");
		int x = rand.nextInt(10); //GENERATE RANDOM NUMBERS BETWEEN 0 AND 9
		int y = rand.nextInt(10);
		
		if(twoD[x][y] == 1) {  //user ship stores a 1
			twoD[x][y] = 5; //five will equal an x on screen
			EndShipNum = 8; //used in game play method later on
			System.out.println("\t\t***The Computer sunk one of your ships!***");	
		}
		else if(twoD[x][y] == 4) {
			twoD[x][y]= 0 ;
			EndShipNum = 9; //
			System.out.println("\t\t***The Computer sunk one of its own ships***");	
		}
		else if(twoD[x][y]== 6 ) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);
			compTurn(twoD);
		}
		else {
			twoD[x][y]= 6 ;
			System.out.println("\t\t ***The Computer missed! ***");	
		}
		oceanMap(twoD);
		return EndShipNum;	
		
	}//playerTurn ends here...
	
	/* Method gamePlay:
	 *    Input: 
	 *        Integer[] [] array: 2d array declared in main
	 *        Scanner in: Scanner opened in main
	 *    Process:
	 *         Call palyerTurn method and compTurn respectively to give computer
	 *         and user turns one by as long as on of the ships equal 0
	 *         Also track the number of ships to close the game once either ship is 0
	 *         Use the tracked ship number to declare the winner
	 *    Output:
	 *        Print outputs from playerTurn and compTurn methods
	 *        At the end inform the user if they won or not.
	 * 
	 */
	
	public static void gamePlay(Integer[] [] array, Scanner in) {
		int x; //used to store return of playerTurn method
		int y;
		int userShip = 5; //number of user ships initially
		int compShip = 5; //number of computer ships initially
		//use some while statement
		do {
            //print user and computer ships first
			System.out.println("User Ships: " + userShip +"\t\t\tComputer Ships: " + compShip);
			
			x = playerTurn(array,in); //player turn is always first
			if( x == 9) --compShip;
			else if(x == 8) --userShip;
			else;
			
			y = compTurn(array); //computer turn
			if( y == 9) --compShip;
			else if(y == 8) --userShip;
			else;
			
		}while(userShip > 0 && compShip > 0);
			
	    //to inform user if they win or lose

		if (userShip == 0) { //if user loses
			System.out.println("");
			System.out.println("\t\t\nYou lost! Good Luck next time ");
		}
		else if( compShip == 0) { //if user wins
			System.out.println("");
			System.out.println("\t\tYou beat the computer \n \t\t\t***CONGRATS! ***");
		}

	}//gamePlay ends here...
	

}//class ends here...
