import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.internal.ev3.EV3Battery;
import lejos.robotics.RangeFinderAdapter;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.PilotProps;

public class Main {
	/*static Port leftEV3MotorPort; 
	static Port rightEV3MotorPort;
	static Port EV3UltrasonicSensorPort;*/
	static Wheel leftWheel = WheeledChassis.modelWheel(Motor.B,56).offset(-53.2);
    static Wheel rigthWheel = WheeledChassis.modelWheel(Motor.D, 56).offset(53.2);
    static Arbitrator arb;
    static MovePilot pilot;
    static Boolean clicked = false;
	public static void main(String[] args) {
	
		go();
		}
		public static void go () 
		{
			Sound.setVolume(100);
			EV3 brick = (EV3) BrickFinder.getDefault();
			
			RangeFinderAdapter ultrasonicAdapter = new RangeFinderAdapter(new EV3UltrasonicSensor(brick.getPort("S4")));
			Chassis chassis = new WheeledChassis(new Wheel[] {leftWheel, rigthWheel},WheeledChassis.TYPE_DIFFERENTIAL);
			pilot =new MovePilot(chassis);
			
			Behavior forward = new GoForward(pilot);
			Behavior ultrasonic  = new Ultrasonic(ultrasonicAdapter,pilot);
			Behavior stopEverything = new StopBeh(Button.ESCAPE);
			Behavior[] behaiverArray = { forward, ultrasonic,stopEverything};
	        arb = new Arbitrator(behaiverArray);
			arb.go();
			
		
	
	}

}


