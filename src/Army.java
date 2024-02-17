import Turtle.*;
import java.util.Scanner;

public class Army {

	public static Turtle[] army = new Turtle[5];

	public static Turtle createTurtle(int userChoice) {

		Turtle t = null;

		if (userChoice == 1) {
			t = new Turtle();}

		if (userChoice == 2) {
			t = new SmartTurtle();}

		if (userChoice == 3) {
			t = new DrunkTurtle();}

		if (userChoice == 4) {
			t = new JumpyTurtle();}

		if (userChoice == 5) {
			t = new EightTurtle();}

		if (userChoice == 6) {
			t = new SatlanTurtle();}

		return t;

	}

	public static void OrderInLine() {

		//locating first turtle in line
		army[0].show();
		for (int i = 1; i < 5; i++) {

			//if it is SatlanTurtle move normally to be ordered in line
			if(army[i] instanceof SatlanTurtle) {
				OrderSatlanInline(((SatlanTurtle)army[i]),i);
			}
			//if it is DrunkTurtle move normally to be ordered in line
			else if (army[i] instanceof DrunkTurtle) {
				OrderDrunkInline(((DrunkTurtle)army[i]),i);
			}

			else {
				army[i].hide();
				army[i].tailUp();
				army[i].turnRight(90);
				army[i].moveForward(120 * i);
				army[i].turnLeft(90);
				army[i].show();
				}
			}
	}
	
	public static void OrderSatlanInline(SatlanTurtle t, int i) {
		
		t.tailUpSachi();
		t.hideSachi();
		t.turnRightSachi(90);
		t.moveSachi(120*i);
		t.turnLeftSachi(90);;
		t.showSachi();
		t.tailDown();
		
	}
	
	public static void OrderDrunkInline(DrunkTurtle t, int i) {
		
		t.tailUp();
		t.hide();
		t.turnRightSachi(90);
		t.moveSachi(120 * i);
		t.turnLeftSachi(90);
		t.show();
	}

	public static void armyTailDown() {

		for (int i = 0; i < 5; i++) {
			army[i].tailDown();
		}
	}

	public static void armyMoveForward(double distance) {

		for (int i = 0; i < 5; i++) {
			army[i].moveForward(distance);
		}
	}

	public static void armyTurnLeft(int angle) {

		for (int i = 0; i < 5; i++) {
			army[i].turnLeft(angle);
		}
	}

	public static void armyDraw (int sides, double size) {

		//only Smart,Jumpy and Eight turtle can draw
		for (int i = 0; i < 5; i++) {

			if (army[i] instanceof SmartTurtle) {
				((SmartTurtle) army[i]).draw(sides, size);
			}

			else if (army[i] instanceof JumpyTurtle) {
				((JumpyTurtle) army[i]).draw(sides, size);
			}

			else if (army[i] instanceof EightTurtle) {
				((EightTurtle) army[i]).draw(sides, size);
			}
		}
	}

	public static void armyHide() {

		for (int i = 0; i < 5; i++) {

			//in order to assure that SatlanTurtle is hide- use its "sachi" option
			if (army[i] instanceof SatlanTurtle) {
				((SatlanTurtle) army[i]).hideSachi();}

			army[i].hide();
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//user chooses its army
		for (int i = 0; i < 5; i++) {

			System.out.println("Choose the type of a turtle:\r\n" + "1.	Simple\r\n" + "2.	Smart\r\n"
					+ "3.	Drunk\r\n" + "4.	Jumpy\r\n" + "5.	Eight\r\n" + "6.	Satlan\r\n" + "");

			int userChoice = sc.nextInt();

			//input validation
			while(userChoice<0 || userChoice>6) {
				System.out.println("Invalid option please choose again:");
				userChoice = sc.nextInt();
			}

			Turtle t = createTurtle(userChoice); //creating new turtle according to user's choice
			army[i] = t;
		}

		OrderInLine();
		armyTailDown();
		armyMoveForward(65);
		armyTurnLeft(40);
		armyMoveForward(75);
		armyDraw(6, 40);
		armyHide();

		sc.close();


	}


}
