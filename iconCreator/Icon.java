package iconCreator;
import java.lang.Thread;

public class Icon {
	public static void main(String[] args) {
		Square footleft = new Square(115,170);
		Square footright = new Square(155,170);
		Circle body = new Circle(100,90,100);
		Circle belly = new Circle(125,115,50);
		Triangle face = new Triangle(150,105,-50,90);
		Circle eyeleft = new Circle(105,65,45);
		Circle eyeright = new Circle(150,65,45);
		Circle innerleft = new Circle(110,70,35);
		Circle innerright = new Circle(155,70,35);
		/*Circle leftpupil = new Circle(120,80,15);
		Circle rightpupil = new Circle(165,80,15);*/
		footleft.makeVisible();
		footleft.changeColor("orange");
		footright.makeVisible();
		footright.changeColor("orange");
		body.makeVisible();
		body.changeColor("brown");
		belly.makeVisible();
		belly.changeColor("orange");
		face.makeVisible();
		face.changeColor("orange");
		eyeleft.makeVisible();
		eyeleft.changeColor("black");
		eyeright.makeVisible();
		eyeright.changeColor("black");
		innerleft.makeVisible();
		innerleft.changeColor("white");
		innerright.makeVisible();
		innerright.changeColor("white");
		/*leftpupil.makeVisible();
		leftpupil.changeColor("black");
		rightpupil.makeVisible();
		rightpupil.changeColor("black");*/
		
		MovementLeft movementleft = new MovementLeft();
		MovementRight movementright = new MovementRight();
		
		//leftpupil.makeInvisible();
		movementleft.start();
		//rightpupil.makeInvisible();
		movementright.start();
		
		/*leftpupil.slowMoveHorizontal(-15);
		rightpupil.slowMoveHorizontal(-15);
		leftpupil.slowMoveHorizontal(30);
		rightpupil.slowMoveHorizontal(-15);
		leftpupil.slowMoveHorizontal(30);
		rightpupil.slowMoveHorizontal(-15);*/
	}
}