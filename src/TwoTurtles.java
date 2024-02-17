import Turtle.*;

public class TwoTurtles {

	public static void main(String[] args) {
		Turtle mario = new Turtle();
		mario.tailDown();
		mario.moveForward(100);
		mario.turnLeft(225);
		mario.moveForward(70);
		mario.turnLeft(90);
		mario.moveForward(70);
		mario.turnRight(135);
		mario.moveForward(100);

		Turtle luigi = new Turtle();
		luigi.hide();
		luigi.tailUp();
		luigi.turnRight(90);
		luigi.moveForward(140);
		luigi.turnLeft(90);
		luigi.moveForward(100);
		luigi.show();
		luigi.tailDown();
		luigi.turnRight(180);
		luigi.moveForward(100);
		luigi.turnLeft(90);
		luigi.moveForward(70);

	}

}
