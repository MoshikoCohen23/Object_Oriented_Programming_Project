
public class JumpyTurtle extends SmartTurtle {

	public static boolean tailDown; //indicate if tail is up or down

	// Constructor
	public JumpyTurtle() {
		super();
		tailDown = true; // each turtle is created with tail down
	}

	//each time JumpyTurtle will perform tail up - our indication will change accordingly
	public void tailUp() {
		tailDown = false;
		super.tailUp();
	}

	//each time JumpyTurtle will perform tail down - our indication will change accordingly
	public void tailDown() {
		tailDown = true;
		super.tailDown();

	}

	public void moveForward(double distance) {
		
		//if tail down - JumpyTurtle would move in short lines  
		if (tailDown) {

			int partialWay = (int)distance/10; //the number of sections the turtle will move

			for (int i = 0; i < partialWay; i++) {

				tailDown();
				super.moveForward(5);
				tailUp();
				super.moveForward(5);

			}
			
			tailDown();//returns to original tail position

		}
		//if tail up - JumpyTurtle will move in a way that does not reveal its path
		else
			super.moveForward(distance);

	}

}
