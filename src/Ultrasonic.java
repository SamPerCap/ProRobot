import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Ultrasonic implements Behavior {
	private  EV3UltrasonicSensor ultraSonicSensor;
	static MovePilot pilot;
	private static boolean suppressed;
	private RangeFinderAdapter ultrasonicAdapter;
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return ultrasonicAdapter.getRange()<20;
	}

	public Ultrasonic(RangeFinderAdapter rangefinderAdaptor, MovePilot pilot ) {
		this.ultrasonicAdapter = rangefinderAdaptor;
		this.pilot= pilot;
	}

	@Override
	public void action() {
		suppressed = false;
		pilot.rotate(45);
		while(!suppressed && pilot.isMoving())
		Thread.yield();
		
		pilot.stop();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		// TODO Auto-generated method stub
		
	}

}
