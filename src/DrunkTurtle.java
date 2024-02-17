import Turtle.Turtle;

public class DrunkTurtle extends Turtle {

	// Constructor
	public DrunkTurtle() {
		super();
	}

	
	public void moveForward(double distance) {
		super.moveForward(Math.random() * distance); //drunk turtle moves to a random distance between 0 - distance
		double rand = Math.random();
		if (rand <= 0.3)
			super.turnLeft((int) distance); //drunk turtle turns left in probability of 30%

		super.moveForward(Math.random() * 0.5 * distance); //drunk turtle moves additional random distance between 0 - distance/2
	}

	//drunk turtle turns left in a random angle between y to 1.5 y
	public void turnLeft(int y) {

		super.turnLeft((int) (Math.random() * 1.5 * y));
	}

	//drunk turtle turns right in a random angle between y to 1.5 y
	public void turnRight(int y) {

		super.turnRight((int) (Math.random() * 1.5 * y));
	}

	//in order to allow the option to move drunk turtle to a necessary position- a call to the original moveForward
	public void moveSachi(double distance) {

		super.moveForward(distance);

	}

	//in order to allow the option to move drunk turtle to a necessary position- a call to the original turnRight
	public void turnRightSachi(int angle) {

		super.turnRight(angle);

	}
	//in order to allow the option to move drunk turtle to a necessary position- a call to the original turnLeft
	public void turnLeftSachi(int angle) {

		super.turnLeft(angle);

	}

}
