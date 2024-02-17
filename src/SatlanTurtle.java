
public class SatlanTurtle extends DrunkTurtle {

	// Constructor
	public SatlanTurtle() {
		super();
	}

	//random num which indicates how SatlanTurtle would behave 
	static double rand = Math.random();

	public void moveForward(double distance) {

		//in 30% of cases SatlanTurtle would behave exactly as DrunkTurtle
		if (rand <= 0.3) {
			super.moveForward(distance);

		}
		//in 40% of cases SatlanTurtle would behave as simple Turtle
		if (rand > 0.3 && rand <= 0.7) {
			super.moveSachi(distance);
		}
		
		//in the rest 30% SatlanTurtle would do nothing

	}

	public void turnRight(int angle) {

		if (rand <= 0.3) {
			super.turnRight(angle);

		}

		if (rand > 0.3 && rand <= 0.7) {
			super.turnRightSachi(angle);
		}

	}

	public void turnLeft(int angle) {

		if (rand <= 0.3) {
			super.turnLeft(angle);
		}

		if (rand > 0.3 && rand <= 0.7) {

			super.turnLeftSachi(angle);
		}
	}
	
	public void tailUp() {
		
		if (rand >= 0.7) {
			super.tailUp();
		}
	}
	
	public void tailDown() {
		
		if (rand >= 0.7) {
			super.tailDown();
		}
	}
	
	public long show() {
		
		if (rand >= 0.7) {
			return super.show();
		}
		
		else
			return 0;
	}
	
	public long hide() {
		
		if (rand >= 0.7) {
			return super.hide();
		}
		
		else
			return 0;
	}
	
	//in order to allow the option to certainly hide SatlanTurtle
	public long hideSachi() {
		
		return super.hide();
	}
	//in order to allow the option for SatlanTurtle to certainly move up its tail
	public void tailUpSachi() {
		
		super.tailUp();
	}
	//in order to allow the option to certainly show SatlanTurtle
	public long showSachi() {
		
		return super.show();
	} 
	
	
	

}
