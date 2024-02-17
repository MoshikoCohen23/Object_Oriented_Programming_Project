import java.util.Scanner;

public class Assignment1 {

	public static Scanner sc = new Scanner(System.in);
	public static boolean isGameOver=false; //boolean variable checking if game is over- win or lose
	public static char[][] gameBoard = new char[9][9]; //creating the game board
	public static char [][] usergameBoard = new char[9][9]; //creating the game board presented to the user
	public static double mines = 10;//number of mines left to place on board
	public static double leftSquares = 81; 

	//placing mines on board according to the algorithm 
	public static void minessAlgoritem () { 

		mines= 10;
		leftSquares =81;
		for (int i =0; i<gameBoard.length ;i++) {
			for (int j=0; j < gameBoard[i].length; j++ ) {

				if((mines>0) && canPlaceMines()){ 

					placeMines(i,j);
				}

				leftSquares = leftSquares - 1; //decreasing the number of squares left to place mines at
			}
		}
	}

	//calculating the probability to place a mine
	public static double probability() {

		return mines/leftSquares;
	}

	//checking if it is valid to place a mine according to probability
	public static boolean canPlaceMines () {

		double random = Math.random(); 
		if (random <= probability())
			return true;

		else return false;
	}

	public static void placeMines(int i,int j) {

		gameBoard[i][j] = '@';
		mines = mines - 1;
	}

	public static int isAbove (int i, int j) {

		if (gameBoard[i-1][j]== '@') 
			return 1;

		else return 0;
	}

	public static int isBelow (int i, int j) {

		if (gameBoard[i+1][j]== '@') 
			return 1;

		else return 0;
	}

	public static int isRight (int i, int j) {

		if (gameBoard[i][j+1] == '@') 
			return 1;

		else return 0;
	}

	public static int isLeft (int i, int j) {

		if (gameBoard[i][j-1]== '@') 
			return 1;

		else return 0;
	}

	public static int isRightAbove (int i, int j) {

		if (gameBoard[i-1][j+1]== '@') 
			return 1;

		else return 0;
	}

	public static int isRightBelow (int i, int j) {

		if (gameBoard[i+1][j+1]== '@') 
			return 1;

		else return 0;
	}

	public static int isLeftAbove (int i, int j) {

		if (gameBoard[i-1][j-1]== '@') 
			return 1;

		else return 0;
	}	

	public static int isLeftBelow (int i, int j) {

		if (gameBoard[i+1][j-1]== '@') 
			return 1;

		else return 0;
	}	

	//placing numbers on game board according to mines location
	public static void boardGameUpdate () {

		int sum;
		for (int i =0; i<gameBoard.length ;i++) {
			for (int j=0; j < gameBoard[i].length; j++ ) {	

				if(gameBoard[i][j] != '@') {
					sum = calcSurroundingMines(i,j);

					//placing sum on board
					placeSumToSqare(sum, i, j);

				}
			}
		}
	}
	//calculating number of mines around this cell according to its' column
	public static int calcSurroundingMines(int i,int j) {
		int sum = 0;

		//left edge of the board
		if(j==0)
			sum = calcLeftEdgeMines(i,j,sum);

		//right edge of the board
		else if(j==gameBoard.length-1)
			sum = calcRightEdgeMines(i,j,sum);

		//in between the board edges
		else if(j>0 && j<gameBoard.length)
			sum = calcCenterMines(i,j,sum);

		return sum;

	}

	public static int calcLeftEdgeMines(int i, int j, int sum) {

		if(i>=1 &&  i<gameBoard.length-1) {
			sum = isRight(i, j) + isRightAbove(i, j) + isRightBelow(i, j) + isAbove(i, j) + isBelow(i, j);
		}

		if(i==0) {
			sum= isRight(i,j) + isRightBelow(i, j) + isBelow(i, j);
		}

		if(i==gameBoard.length-1) {
			sum= isRight(i,j) + isRightAbove(i, j) + isAbove(i, j);
		}
		return sum;
	}

	public static int calcRightEdgeMines(int i, int j, int sum) {

		if(i>=1 &&  i< gameBoard.length-1) {
			sum = isLeft(i, j) + isLeftAbove(i, j) +isLeftBelow(i, j) + isAbove(i, j) + isBelow(i, j);
		}

		else if(i==0) {
			sum = isLeft(i,j) + isLeftBelow(i, j) + isBelow(i, j);
		}

		else if(i==gameBoard.length-1) {
			sum = isLeft(i,j) + isLeftAbove(i, j) + isAbove(i, j);
		}
		return sum;
	}

	public static int calcCenterMines (int i, int j, int sum) {

		if (i>=1 && i<=7) {
			sum = isLeft(i, j) + isRight(i,j) + isAbove(i, j) + isBelow(i, j) + isRightAbove(i, j) +isLeftAbove(i, j) + isRightBelow(i, j) +isLeftBelow(i, j); 
		}

		if(i==0) {

			sum = isLeft(i, j) + isRight(i,j) + isBelow(i, j) + isRightBelow(i, j) +isLeftBelow(i, j);
		}

		if(i==8) {

			sum = isLeft(i, j) + isRight(i,j) + isAbove(i, j) + isRightAbove(i, j) +isLeftAbove(i, j);
		}

		return sum;
	}

	//placing mine on board
	public static void placeSumToSqare(int sum, int i, int j) {

		//casting
		String s = Integer.toString(sum);
		gameBoard[i][j]=s.charAt(0);
	}

	public static void markMine(int i, int j) {

		if(usergameBoard[i][j] != '#') {// if square is already marked or exposed
			System.out.println("Invalid option! try again");
			return;}


		else
			usergameBoard[i][j]= '&';
	}

	public static void unMarkMine(int i, int j) {

		if(usergameBoard[i][j] != '&') {// if square is not marked
			System.out.println("Invalid option! try again");
			return;}

		else
			usergameBoard[i][j]= '#';
	}

	public static boolean isMine (int i, int j) {

		if(gameBoard[i][j] == '@')
			return true;

		return false;
	}

	public static boolean isNum(int row, int column) {

		if(gameBoard[row][column]!='0')
			return true;

		else return false;
	}

	public static void exposeCell(int i, int j) {

		if(gameBoard[i][j]=='0' || gameBoard[i][j] =='9')
			usergameBoard[i][j] = ' '; //if cell is 0 or already checked- mark as blank

		else
			usergameBoard[i][j] = gameBoard[i][j];

	}

	//exposing blank squares on board
	public static void exposeBlanks(int row, int column) {

		//starts only if row and column are on the board's borders
		if(row >=0 && row<=8 && column >=0 && column<=8) {

			if(isNum(row,column)) {
				exposeCell(row,column);
				return;
			}

			else {
				gameBoard[row][column]='9';
				exposeCell(row,column);
				exposeBlanks(row+1, column); // down
				exposeBlanks(row, column+1); // right
				exposeBlanks(row+1, column+1); // down-right
				exposeBlanks(row-1, column); // down
				exposeBlanks(row, column-1); // left
				exposeBlanks(row-1, column-1); // left-up
				exposeBlanks(row+1, column-1); // left-up
				exposeBlanks(row+1, column+1); // right-down

			}		
		}
	}

	public static void createUserBoard () {

		//creating user's board with #
		for (int i =0; i<usergameBoard.length ;i++) {
			for (int j=0; j < usergameBoard[i].length; j++ ) {
				usergameBoard[i][j] = '#';
			}
		}		
	}

	public static void createGameBoard() {

		minessAlgoritem(); //placing mines on board according to the algorithm
		boardGameUpdate(); //placing numbers on board according to the mines location

	}

	public static void startGame() {

		//welcome to the game
		System.out.println("Welcome to Fatma Minesweeper to start the game press 'enter'");
		sc.nextLine();

		createGameBoard();//creating game board  
		createUserBoard(); //creating user board

	}

	public static int userInput() {

		System.out.println("Please enter an operation number and its location\r\n" + 
				"(1=select a square, 2= mark a mine, 3= unmark a mine)\r\n" + 
				"");
		int userNum = sc.nextInt();

		return userNum;
	}

	public static int extractUserAction(int userNum) {
		return userNum/100;
	}
	public static int extractUserRow(int userNum) {
		return (userNum/10)%10;
	}

	public static int extractUsercolumn(int userNum) {
		return userNum%10;
	}

	//Performing user turn according to the chosen action and location on board
	public static void actions(int action, int row, int column) {

		if(action == 1) {

			if(usergameBoard[row][column]!= '#') {
				System.out.println("Invalid option! try again");
				return;

			}

			else if(isMine(row,column)) {
				isGameOver = true;
			}

			else if(isNum(row,column)) {
				exposeCell(row, column);
			}

			else exposeBlanks(row,column);
		}


		else if(action == 2) {
			markMine(row, column);
		}

		else if(action == 3) {
			unMarkMine(row, column);
		}
	}

	public static boolean isNotMine (int row, int column) {

		if (usergameBoard[row][column]!= '#' && usergameBoard[row][column]!='&') {
			return true;
		}

		return false;
	}

	//checking if the user won the game and expose all the squares which without mines
	public static boolean isWinning() {

		int count = 0;
		for(int i=0; i<usergameBoard.length; i++) {
			for( int j=0; j<usergameBoard[i].length; j++) {

				if(isNotMine(i,j)) {
					count++;
				}
			}

			if(count==71) {
				return true;
			}
		}
		return false;
	}

	//checking the user's will to play another game
	public static boolean keepPlaying() {

		System.out.println("Do you want to start a new game?\nPress 1= yes, 2= no");
		int keepPlaying = sc.nextInt();

		if(keepPlaying == 1)
			return true;
		else
			return false;
	}

	public static void printBoard (char [][] arr) {

		System.out.print("the board:");
		for (int i =0; i<arr.length ;i++) {
			System.out.println();
			for (int j=0; j < arr[i].length; j++ ) {	
				System.out.print(arr[i][j] +" ");

			}

		}
		System.out.println();
	}

	public static void main(String[] args) {

		boolean game = true; //boolean variable to keep the game running

		while(game) {

			isGameOver=false;
			startGame(); //Initializing game boards

			while(!isGameOver) {

				printBoard(usergameBoard); //printing user board

				int userNum=userInput(); 

				//starting user's turn
				actions(extractUserAction(userNum), extractUserRow(userNum), extractUsercolumn(userNum));


				if(isGameOver) {
					System.out.println("Boom!!! You've hit a mine and lost…");
					if(!keepPlaying()) {
						game=false;
						System.out.println("Game Is Over");
					}
				}

				if(isWinning()) {
					isGameOver=true;
					System.out.println("Congratulations, you won :)");
					if(!keepPlaying()) {
						game = false;
						System.out.println("Game Is Over");
					}
				}

			}
		}

	}
}
