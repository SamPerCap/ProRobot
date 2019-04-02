import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.internal.ev3.EV3Battery;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.TouchAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.PilotProps;

public class ExplorerBrick {
	/*static Port leftEV3MotorPort; 
	static Port rightEV3MotorPort;
	static Port EV3UltrasonicSensorPort;*/
	Wheel leftWheel = WheeledChassis.modelWheel(Motor.C,56).offset(-53.2);
    Wheel rigthWheel = WheeledChassis.modelWheel(Motor.D, 56).offset(53.2);
    Wheel sonicWheel = WheeledChassis.modelWheel(Motor.B, 30).offset(0);
    Arbitrator arb;
    MovePilot pilot;
    Boolean clicked = false;
    TouchAdapter rightAdapter;
    TouchAdapter leftAdapter;
	private static int acceleration = 50;
	public static void main(String[] args) {
		ExplorerBrick eb = new ExplorerBrick();
		eb.go();
		}
	
		public  void go () 
		{
			Sound.setVolume(100);
			EV3 brick = (EV3) LocalEV3.get();
			try(EV3TouchSensor rightSensor = new EV3TouchSensor(brick.getPort("S2"));
					EV3TouchSensor leftSensor = new EV3TouchSensor(brick.getPort("S1"));
					EV3UltrasonicSensor ultrasonicSensor = new EV3UltrasonicSensor(brick.getPort("S4"))){
				rightAdapter = new TouchAdapter(rightSensor);
				leftAdapter = new TouchAdapter(leftSensor);
			
			RangeFinderAdapter ultrasonicAdapter = new RangeFinderAdapter(ultrasonicSensor);
			
			
			Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rigthWheel},WheeledChassis.TYPE_DIFFERENTIAL);
			pilot =new MovePilot(chassis);
			Behavior bumper = new Bumper(pilot, rightAdapter, leftAdapter) ; 
			Behavior forward = new GoForward(pilot);
			Behavior ultrasonic  = new Ultrasonic(ultrasonicAdapter,pilot, sonicWheel);
			Behavior stopEverything = new StopBeh(Button.ESCAPE);
			Behavior[] behaiverArray = {forward, ultrasonic, bumper, stopEverything};
	        arb = new Arbitrator(behaiverArray);
			arb.go();
			}
	}
		
		public static int getAcc() {
			return acceleration;
		}

}


