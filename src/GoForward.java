
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class GoForward implements Behavior{
	
	MovePilot pilot;
	Boolean suppressed;
	int speed = 100;
	
	public GoForward(MovePilot pilot) {
		this.pilot = pilot;
		pilot.setAngularAcceleration(ExplorerBrick.getAcc());
		pilot.setAngularSpeed(speed);
		pilot.setLinearSpeed(speed);
	}
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		suppressed = false;
		pilot.forward();
		while(!suppressed)
			Thread.yield();
		pilot.stop();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppressed = true;
	}

}
