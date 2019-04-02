import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class StopBeh implements Behavior {
	public boolean clicked = false;
	 private static final short [] note = {
		      2349,115, 0,5, 1760,165, 0,35};
	public StopBeh (Key escape) {
		//this.clicked = escape;
		Button.ESCAPE.addKeyListener(new lejos.hardware.KeyListener() {

			@Override
			public void keyPressed(Key k) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyReleased(Key k) {
				clicked = true;
				
			}
		});
	}
	private boolean suppressed = false; 
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return clicked;
	}

	@Override
	public void action() {
		suppressed = false;
	  Sound.beep();
	  play();
		// TODO Auto-generated method stub
	  System.exit(0);
	}
	public void play() {
	      for(int i=0; i<note.length; i+=2) {
	         final short w = note[i+1];
	         Sound.playTone(note[i], w);
	         Sound.pause(w*10);
	         if (suppressed)
	        	 return; // exit this method if suppress is called
	      }
	   }

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		suppressed = true;
	}

}
