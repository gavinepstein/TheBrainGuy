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

public class VisualDisplay extends PApplet   {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 300;
	public static int Mouse_X;
	public static int Mouse_Y;
	public static boolean MouseDown;
	public static final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	
	public static JPanel panel = new JPanel();
	//mouse listener
	
			
			
			
	public static final JLabel picLabel = new JLabel();
	float t=0;
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
		
		
		//TODO change this
		t+=(float)1/10;
		colorMode(HSB, 100);
		fill(color(t%100,100,100));
		stroke(color(t%100,100,100));
		 
		
		if (MouseDown){
			ellipse(Mouse_X, Mouse_Y,10,10);
		}
		fill(color(0,0,0));
		//ellipse(mouseX, mouseY, 30*  (float)sin(t),30*(float)cos(t));
		loadPixels();
		//put pixels form the image into a public array
		
		img.setRGB(0, 0, WIDTH,HEIGHT, pixels, 0, WIDTH);
		picLabel.setIcon(new ImageIcon(img));
		
	}
	
	
	
	
	public static JPanel getDrawing(int width, int height){
		//Add mouse interaction
		panel.addMouseMotionListener( new MouseMotionListener(){

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
		VisualDisplay sketch = new VisualDisplay();
		String[] args = {"VisualDisplay "};
		PApplet.runSketch(args, sketch);
		
		/*
		//get the PSurface from the sketch
		PSurface sketchSurface = sketch.initSurface();

		//initialize the PSurface
		sketchSurface.setSize(width, height);
        
		//get the SmoothCanvas that holds the PSurface
		PSurfaceAWT awtSurface = (PSurfaceAWT)sketch.surface;
		PSurfaceAWT.SmoothCanvas smoothCanvas = (PSurfaceAWT.SmoothCanvas)awtSurface.getNative();
		//SmoothCanvas can be used as a Component
		panel.add(smoothCanvas);

		//return Frame
		 * 
		 */
		//
		
		
		panel.add(picLabel);
		panel.setSize(width, height);
		return panel;
	}
	//TODO mouse listener
	

}
