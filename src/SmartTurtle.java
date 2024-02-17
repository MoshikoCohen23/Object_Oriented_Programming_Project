import Turtle.*;

public class SmartTurtle extends Turtle {

	// Constructor
	public SmartTurtle() {
		super();
	}

	public void draw(int sides, double size) {

		int angle = ((sides - 2) * 180) / sides; //calculating the angle needed to be in the polygon according to its sides
		turnRight(90);

		for (int i = 1; i <= sides; i++) {
			turnLeft(180 - angle);
			moveForward(size);
		}
	}

}
