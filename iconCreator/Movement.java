package iconCreator;

class MovementLeft extends Thread {
	public void run() {
		Circle leftpupil = new Circle(120,80,15);
		leftpupil.makeVisible();
		leftpupil.changeColor("black");
		leftpupil.slowMoveHorizontal(-15);
		leftpupil.slowMoveHorizontal(30);
		leftpupil.slowMoveHorizontal(-15);
	}
}

class MovementRight extends Thread {
	public void run() {
		Circle rightpupil = new Circle(165,80,15);
		rightpupil.makeVisible();
		rightpupil.changeColor("black");
		rightpupil.slowMoveHorizontal(-15);
		rightpupil.slowMoveHorizontal(30);
		rightpupil.slowMoveHorizontal(-15);
	}
}