import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.TouchAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Bumper implements Behavior{
	
	TouchAdapter right;
	TouchAdapter left;
	MovePilot pilot;
	int direction;
	boolean suppress = false;
	
	public Bumper (MovePilot pilot, TouchAdapter rightSensor, TouchAdapter leftSensor) {
		this.right = rightSensor;
		this.left = leftSensor;
		this.pilot = pilot;
	}
	

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return right.isPressed() || left.isPressed();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		pilot.travel(50);
		direction = ((int) (Math.random()));
		switch (direction) {
		case 0:
			pilot.rotateRight();
			break;
		case 1:
			pilot.rotateLeft();
			break;
		}
		while(pilot.isMoving() && !suppress)
			Thread.yield();
		pilot.stop();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppress = true;
	}

}
