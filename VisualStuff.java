

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import processing.core.PApplet;
import processing.core.PImage;

public class VisualStuff extends PApplet {
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 300;
	public static int Mouse_X;
	public static int Mouse_Y;
	public static boolean MouseDown;
	public static final BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);	
	public static boolean clearNow = false;
	public static JPanel panel = new JPanel();
	public static int lastX;
	public static int lastY;
	private static boolean loaded = false;
	private static String filename;
	//mouse listener
	
	public static final JLabel picLabel = new JLabel();
	
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
		//loading image
		if (loaded){
			try{
			PImage image = loadImage(filename);
			
			image(image,0,0,WIDTH * image.height/image.width,HEIGHT);
			} 
			catch (NullPointerException e){
				System.out.println("can't use that file");
			}
			
			
			loaded = false;
		}
		
		//TODO put in processing code for nice thing here :) :) 
		colorMode(HSB, 100);

		// color is based off of the ColorChooser's values (finally)
		fill(ColorChooser.tcc.getColor().getRGB());
		stroke(ColorChooser.tcc.getColor().getRGB());
				
		if (MouseDown == true) { 
			//stroke(255);
			strokeWeight(0);
			
			
			if (lastX!=-1){
				
				strokeWeight(15);
				line(lastX, lastY, Mouse_X, Mouse_Y);
				
			}else{
			//	ellipse(Mouse_X, Mouse_Y, 15, 15); 
			}
			lastX = Mouse_X;
			lastY=Mouse_Y;
		} else{
			lastX=-1;
			lastY=-1;
		
		}
		
		if (clearNow == true) {
			System.out.println("ping");
			background(0, 0, 0);
			clearNow = false;
		}

		loadPixels();
		
		//put pixels from the image into a public array
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
		VisualStuff sketch = new VisualStuff();
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
	
	public static void loadimage(String Filename){
		loaded = true;
		filename = Filename;
			
		
	}
	//TODO mouse listener
	
	
	

}
