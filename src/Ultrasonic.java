import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class Ultrasonic implements Behavior {
	static MovePilot pilot;
	private static boolean suppressed;
	private RangeFinderAdapter ultrasonicAdapter;
	private float distanceone;
	private float distancetwo;
	private int direction;
	private Wheel sonicmotor;
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return ultrasonicAdapter.getRange()<20;
	}

	public Ultrasonic(RangeFinderAdapter rangefinderAdaptor, MovePilot pilot, Wheel motor) {
		this.ultrasonicAdapter = rangefinderAdaptor;
		this.pilot= pilot;
		this.sonicmotor = motor;
		pilot.setAngularAcceleration(ExplorerBrick.getAcc());
	}

	@Override
	public void action() {
		suppressed = false;
		sonicmotor.getMotor().rotate(45);
		distanceone = ultrasonicAdapter.getRange();
		sonicmotor.getMotor().rotate(-90);
		distancetwo = ultrasonicAdapter.getRange();
		if(distanceone <= 25 && distancetwo <= 25) {
			pilot.travel(-50);
			direction = ((int) (Math.random() * 2));
			switch (direction) {
			case 0:
				pilot.rotateLeft();
				break;
			case 1:
				pilot.rotateRight();
				break;
			}
		}else {
			if(distanceone > distancetwo) {
			pilot.rotate(50);
		}else if(distanceone < distancetwo){
			pilot.rotate(-50);
		}
		}
		sonicmotor.getMotor().rotate(45);
		while(!suppressed && pilot.isMoving())
		Thread.yield();
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suppress() {
		suppressed = true;
		// TODO Auto-generated method stub
		
	}

}
