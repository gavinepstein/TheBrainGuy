import java.awt.Color;
import java.awt.Container;
import java.awt.Event;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VisualStuff extends PApplet {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 300;
	public static int Mouse_X;
	public static int Mouse_Y;
	public static boolean MouseDown;
	public static final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	
			
	public static JPanel panel = new JPanel();
<<<<<<< HEAD
	public static int lastX;
	public static int lastY;
	private static boolean loaded = false;
	private static String filename;
	public static int sizeValue = 15;
	public static boolean isSquare = false; 
=======
>>>>>>> 6e5bd2d2318a5e95057733404b45548ed9818954
	//mouse listener
	
	public static final JLabel picLabel = new JLabel();
	
	// delete if moot 
	public static boolean isCleared = false; 
	
	public static int sizeValue = 15; 
	
	//size and framerate
	public void settings(){
		size(WIDTH, HEIGHT);
		
	}
	
	//same as Processing setup, but without the things that go in settings
	public void setup(){
		fill(0,0,0);
		rect(0,0,WIDTH,HEIGHT);
		
		
	}
	//same as Processing draw
	public void draw(){
				
		//TODO put in processing code for nice thing here :) :) 
		colorMode(HSB, 100);

		// color is based off of the ColorChooser's values (finally)
		fill(ColorChooser.tcc.getColor().getRGB());
		stroke(ColorChooser.tcc.getColor().getRGB());
				
		if (MouseDown == true) { 
<<<<<<< HEAD
			//stroke(255);
			strokeWeight(0);
			
			if (!isSquare){
			if (lastX!=-1){
				
				strokeWeight(sizeValue);
				line(lastX, lastY, Mouse_X, Mouse_Y);
				
			}
			lastX = Mouse_X;
			lastY=Mouse_Y;
			}else {
				rect(Mouse_X, Mouse_Y, sizeValue, sizeValue );
			}
		} else{
			lastX=-1;
			lastY=-1;
		
=======
			stroke(255);
			ellipse(Mouse_X, Mouse_Y, sizeValue, sizeValue); 
>>>>>>> 6e5bd2d2318a5e95057733404b45548ed9818954
		}
		

		loadPixels();
		
		//put pixels from the image into a public array
		img.setRGB(0, 0, WIDTH,HEIGHT, pixels, 0, WIDTH);
		
		int[] pixelClear = new int[width*height];
		for (int i=0; i<width*height; i++) {
			pixelClear[i] = 0;
		}
		
		// clear attempt
		if (isCleared == true) {
			img.setRGB(0, 0, width, height, pixelClear, 0, WIDTH);
			isCleared = false; 
		}
		picLabel.setIcon(new ImageIcon(img));
		
	}
	
	public static JPanel getDrawing(int width, int height){
		//Add mouse interaction
		panel.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				Mouse_X = e.getX();
				Mouse_Y = e.getY();
				MouseDown = true;
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				Mouse_X = e.getX();
				Mouse_Y = e.getY();
				MouseDown = false;
			}
			
			
		});
		
		//Make Processing sketch

		VisualStuff sketch = new VisualStuff();
		String[] args = {"VisualDisplay "};
		PApplet.runSketch(args, sketch);
		
		
		panel.add(picLabel);
		panel.setSize(width, height);
		return panel;
	}
	//TODO mouse listener
	

}
