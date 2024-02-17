
public class EightTurtle extends SmartTurtle {

	// Constructor
	public EightTurtle() {
		super();
	}

	public void draw(int sides, double size) {

		double rand = Math.random();

		//EightTurtle draws is a probability of 30% polygon with same sides but with size =18
		if (rand <= 0.3) {
			super.draw(sides, 18);
		}

		//in probability of 70% - it will draw polygon with same size but with sides =8

		else {
			super.draw(8, size);
		}
	}

}
