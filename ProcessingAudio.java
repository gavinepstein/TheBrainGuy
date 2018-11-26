import java.awt.Color;
	import java.awt.Event;
	import java.awt.Graphics2D;
	import java.awt.event.MouseEvent;
	import java.awt.event.MouseListener;
	import java.awt.event.MouseMotionListener;
	import java.awt.image.BufferedImage;
	import java.util.concurrent.TimeUnit;

	import javax.swing.Icon;
	import javax.swing.ImageIcon;
	import javax.swing.JComponent;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	
	import processing.awt.PSurfaceAWT;
	import processing.awt.PSurfaceAWT.SmoothCanvas;
	import processing.core.PApplet;
	import processing.core.PSurface;
	import processing.sound.*;
public class ProcessingAudio  extends PApplet {
		public static final int WIDTH = 1000;
		public static final int HEIGHT = 300;
		public static int Mouse_X;
		public static int Mouse_Y;
		public static boolean MouseDown;
		
		
		//subclass for sounds.
		public class sounds{
			 String type;
			 int freq;
			 float amp;
			 public sounds(String type, int freq, float amp){
			   this.type = type;
			   this.freq = freq;
			   this.amp = amp;
			 }
			 public String gettype(){
			   return type;
			 }
			 public int getfreq(){
			   return freq;
			 }
			 public float getamp(){
			   return amp;
			 }
			}
		

		public Env env1;

public sounds createsounds(String name, int freq, float amp){
  sounds sound1;
  if (name.equals("sine")){
    //sounds1.type = sine;
    sounds sounds1 = new sounds("sine",freq,amp);
    return sounds1;
  }
  else if (name.equals("triangle")){
    //sounds1.type = triangle;
    sounds sounds1 = new sounds("triangle",freq,amp);
    return sounds1;
  }
  else if (name.equals("square")){
    //sounds1.type = square;
    sounds sounds1 = new sounds("square",freq,amp);
    return sounds1;
  }
  else if (name.equals("pulse")){
    //sounds1.type = pulse;
    sounds sounds1 = new sounds("pulse",freq,amp);
    return sounds1;
  }
  else if (name.equals("saw")){
    //sounds1.type = saw;
    sounds sounds1 = new sounds("saw",freq,amp);
    return sounds1;
  }
  else{
     return null; 
  }
}

//play a sound
private void playSound(sounds note, float[] enveloop){
	  env1  = new Env(this); 
	  if (note.gettype().equals("sine")){
	    SinOsc sound = new SinOsc(this);
	    sound.freq(note.freq);
	    sound.amp(note.amp);
	    env1.play(sound, enveloop[0],enveloop[1],enveloop[2],enveloop[3]);
	  }
	  else if (note.gettype().equals("triangle")){
	    TriOsc sound = new TriOsc(this);
	    sound.freq(note.freq);
	    sound.amp(note.amp);
	    env1.play(sound, enveloop[0],enveloop[1],enveloop[2],enveloop[3]);
	  }
	  else if (note.gettype().equals("square")){
	    SqrOsc sound = new SqrOsc(this);
	    sound.freq(note.freq);
	    sound.amp(note.amp);
	    env1.play(sound, enveloop[0],enveloop[1],enveloop[2],enveloop[3]);
	  }
	  else if (note.gettype().equals("pulse")){
	    Pulse sound = new Pulse(this);
	    sound.freq(note.freq);
	    sound.amp(note.amp);
	    env1.play(sound,enveloop[0],enveloop[1],enveloop[2],enveloop[3]);
	  }
	  else if (note.gettype().equals("saw")){
	    SawOsc sound = new SawOsc(this);
	    sound.freq(note.freq);
	    sound.amp(note.amp);
	    env1.play(sound, enveloop[0],enveloop[1],enveloop[2],enveloop[3]);
	  }
	}


//KEYBINDING
public sounds s1;
public sounds s2;
public sounds s3;
public sounds s4;
public sounds s5;
public sounds s6;
public sounds s7;
public sounds s8;
public sounds s9;
public sounds s10;
public sounds s11;
public sounds s12;
public sounds s13;
public sounds s14;
public sounds s15;
public sounds s16;
public sounds s17;
public sounds s18;
public sounds s19;
public sounds s20;
public sounds s21;
public sounds s22;
public sounds s23;
public sounds s24;
public sounds s25;
public sounds s26;
public float[] e1;
public float[] e2;
public float[] e3;
public float[] e4;
public float[] e5;
public float[] e6;
public float[] e7;
public float[] e8;
public float[] e9;
public float[] e10;
public float[] e11;
public float[] e12;
public float[] e13;
public float[] e14;
public float[] e15;
public float[] e16;
public float[] e17;
public float[] e18;
public float[] e19;
public float[] e20;
public float[] e21;
public float[] e22;
public float[] e23;
public float[] e24;
public float[] e25;
public float[] e26;


public void mapkey(char yek, sounds ss, float[] enveloop){
  if (yek == 'q' || yek == 'Q'){
    s1 = ss;
    e1 = enveloop;
  }
  if (yek == 'w' || yek == 'W') {
     s2 = ss;
    e2 = enveloop;
    }
   if (yek == 'e' || yek == 'E') {
      s3 = ss;
    e3 = enveloop;
    }
    if (yek == 'r' || yek == 'R') {
      s4 = ss;
    e4 = enveloop;
    }
    if (yek == 't' || yek == 'T') {
      s5 = ss;
    e5 = enveloop;
    }
    if (yek == 'y' || yek == 'Y') {
      s6 = ss;
    e6 = enveloop;
    }
    if (yek == 'u' || yek == 'U') {
     s7 = ss;
    e7 = enveloop;
    }
    if (yek == 'i' || yek == 'I') {
      s8 = ss;
    e8 = enveloop;
    }
    if (yek == 'o' || yek == 'O') {
      s9 = ss;
    e9 = enveloop;
    }
    if (yek == 'p' || yek == 'P') {
      s10 = ss;
    e10 = enveloop;
    }
    if (yek == 'a' || yek == 'A') {
      s11 = ss;
    e11 = enveloop;
    }
    if (yek == 's' || yek == 'S') {
      s12 = ss;
    e12 = enveloop;
    }
    if (yek == 'd' || yek == 'D') {
      s13 = ss;
    e13 = enveloop;
    }
    if (yek == 'f' || yek == 'F') {
      s14 = ss;
    e14 = enveloop;
    }
    if (yek == 'g' || yek == 'G') {
      s15 = ss;
    e15 = enveloop;
    }
    if (yek == 'h' || yek == 'H') {
      s16 = ss;
    e16 = enveloop;
    }
    if (yek == 'j' || yek == 'J') {
      s17 = ss;
    e17 = enveloop;
    }
    if (yek == 'k' || yek == 'K') {
      s18 = ss;
    e18 = enveloop;
    }
    if (yek == 'l' || yek == 'L') {
     s19 = ss;
    e19 = enveloop;
    }
    if (yek == 'z' || yek == 'Z') {
      s20 = ss;
    e20 = enveloop;
    }
    if (yek == 'x' || yek == 'X') {
      s21 = ss;
    e21 = enveloop;
    }
    if (yek == 'c' || yek == 'C') {
      s22 = ss;
    e22 = enveloop;
    }
    if (yek == 'v' || yek == 'V') {
      s23 = ss;
    e23 = enveloop;
    }
    if (yek == 'b' || yek == 'B') {
      s24 = ss;
    e24 = enveloop;
    }
    if (yek == 'n' || yek == 'N') {
      s25 = ss;
    e25 = enveloop;
    }
    if (yek == 'm' || yek == 'M') {
      s26 = ss;
    e26 = enveloop;
    }
}


		//size and framerate
		public void settings(){
			size(0, 0);
			
		}
		
		//same as Processing setup, but without the things that go in settings
		public void setup(){
			
			
			
		}
		//same as Processing draw
		public void draw(){
			// Map vertical mouse position to volume.
			  // Instead of setting the volume for every oscillator individually, we can just
			  // control the overall output volume of the whole Sound library.
			  
			  if (keyPressed) {
			    if (key == 'q' || key == 'Q') {
			      playSound(s1,e1);
			    }
			  }
			  if (keyPressed) {
			    if (key == 'w' || key == 'W') {
			      playSound(s2,e2);
			    }
			  }
			  if (keyPressed) {
			    if (key == 'e' || key == 'E') {
			      playSound(s3,e3);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'r' || key == 'R') {
			      playSound(s4,e4);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 't' || key == 'T') {
			      playSound(s5,e5);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'y' || key == 'Y') {
			      playSound(s6,e6);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'u' || key == 'U') {
			      playSound(s7,e7);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'i' || key == 'I') {
			      playSound(s8,e8);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'o' || key == 'O') {
			      playSound(s9,e9);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'p' || key == 'P') {
			      playSound(s10,e10);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'a' || key == 'A') {
			      playSound(s11,e11);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 's' || key == 'S') {
			      playSound(s12,e12);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'd' || key == 'D') {
			      playSound(s13,e13);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'f' || key == 'F') {
			      playSound(s14,e14);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'g' || key == 'G') {
			      playSound(s15,e15);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'h' || key == 'H') {
			      playSound(s16,e16);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'j' || key == 'J') {
			      playSound(s17,e17);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'k' || key == 'K') {
			      playSound(s18,e18);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'l' || key == 'L') {
			      playSound(s19,e19);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'z' || key == 'Z') {
			      playSound(s20,e20);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'x' || key == 'X') {
			      playSound(s21,e21);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'c' || key == 'C') {
			      playSound(s22,e22);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'v' || key == 'V') {
			      playSound(s23,e23);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'b' || key == 'B') {
			      playSound(s24,e24);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'n' || key == 'N') {
			      playSound(s25,e25);
			    }
			  } 
			  if (keyPressed) {
			    if (key == 'm' || key == 'M') {
			      playSound(s26,e26);
			    }
			  } 
			  
			}
			
			
			

			
		}
		
		public static final int NumParams = 7;
		/** Makes parameters based on an image
		*all parameters are floats in [0,1]
		*parameters
		*0 - red
		*1- green
		*2 - blue
		*3 - hue
		*4 - saturation
		*5 - value
		*6 - edge count
		*/
		float [] makeParams(BufferedImage img){
			int width = img.getWidth();
			int height = img.getHeight();
			int size = width*height;
			//returned thing
			float [] ret = new float[NumParams];
			//initialize to 0
			for (int i=0; i<NumParams;i++){
				ret [i]=0;
			}
			//edge detection bitmask, to cover significant digits of each of RGB
			int bitmask = 0b111100001111000011110000;
			//lööp over all pixels
			for (int x  = 0; x<width; x++){
				for (int y = 0; y< height; y++){
					//get pixel data
					 Color color =new Color( img.getRGB(x, y));
					 //edit basic colors
					 ret[0]+= color.getRed();
					 ret[1]+=color.getGreen();
					 ret[2]+=color.getBlue();
					 //saturation value can be done from the averages 
					 //edge detection. 
					 //gets two pixels, compares their differences then masks
					 //to get difference in R or G or B greater than 16
					 //left
					 if(x>0 &&  ((img.getRGB(x-1, y) ^  color.getRGB()  ) &  bitmask) > 0 ){
						 ret[6]+=1;
					 }
					 //right
					 if(x<width-1 &&  ((img.getRGB(x+1, y) ^  color.getRGB()  ) &  bitmask) > 0 ){
						 ret[6]+=1;
					 }
					//top
					 if(y>0 &&  ((img.getRGB(x, y-1) ^  color.getRGB()  ) &  bitmask) > 0 ){
						 ret[6]+=1;
					 }
					 //bottom
					 if(y<height-1 &&  ((img.getRGB(x, y+1) ^  color.getRGB()  ) &  bitmask) > 0 ){
						 ret[6]+=1;
					 }
				}
			}
			//average R, G, B
			ret[0] = ret[0] / size;
			ret[1] = ret[1] / size;
			ret[2] = ret[2] / size;
			//HSV
			float[] hsv = new float[3];
			Color.RGBtoHSB((int)ret[0], (int)ret[1], (int)ret[2], hsv);
			ret[3] = hsv[0];
			ret[4] = hsv[1];
			ret[5] = hsv[2];
			//normalize rgb to [0,1]
			ret[0] = ret[0]/256;
			ret[1] = ret[1]/256;
			ret[2] = ret[2]/256;
			ret[6] = Math.min(1f, ret[6]/(size*2));
			
			return ret;
			
		}
		

		//makes the thing
		public static PApplet MakeAudio(){
			//Add mouse interaction
			

				
				
			
			
			//Make Processing sketch
			ProcessingAudio sketch = new ProcessingAudio();
			
			return sketch;
		}
		//TODO mouse listener
		

	}
	

