import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import javax.swing.event.*;

import processing.core.PApplet;
  

public class Attempt3 extends JFrame implements ChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean clearNow = false;
	// set up buttons for the top of the screen 
	JButton saveButton = new JButton("Save"); 
	JButton clearButton = new JButton("Clear");
	JButton goToKeyboardButton = new JButton("Go to Keyboard");
	JButton playButton = new JButton("Play");
	JButton pauseButton = new JButton("Pause");
	
	//handles all the audio
	ProcessingAudio audioPlayer = new ProcessingAudio();
	// set up title text at the top of the screen
	JLabel screenTop = new JLabel("Create Instrument Mode");
	
	// set up title text for the lower areas
	JLabel drawingToolsLabel = new JLabel("Drawing Tools");
	JLabel adjusterLabel = new JLabel("Adjuster Tools");
	JLabel instrumentSelectLabel = new JLabel("Instrument Select");
	
	//panelz
	JPanel mainPanel = new JPanel();
	// create top panel for box 
	JPanel topPanel = new JPanel();
	// create the center panel to hold the image 
	JPanel centerPanel = new JPanel();
	// create the bottom panel to hold the box 
	JPanel bottomPanel = new JPanel(); 
	
	JPanel bottomLeft = new JPanel();
	JPanel bottomCenter = new JPanel();
	JPanel bottomRight = new JPanel();
	
	// create default list & scroller for the instrument select scroller
	DefaultListModel instrumentSelectList = new DefaultListModel();
	JScrollPane instrumentScroller;
	JList instrumentsList;
	
	// create dimensions for reference
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	
	public Attempt3() {
		// set up the main window size, location, close type, title
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("AV: Instrument Mode");
		
		//Button stuff
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				}
		});
		
		
		// sliders baybeeeee
		// first slider: Drawing tool size adjuster 
		final int SIZE_MIN = 1;
		final int SIZE_MAX = 100;
		
		JSlider drawSize = new JSlider(JSlider.HORIZONTAL,
	           SIZE_MIN, SIZE_MAX, 50);
		drawSize.addChangeListener(this);
		
		drawSize.setMajorTickSpacing(24);
		drawSize.setMinorTickSpacing(14);
		drawSize.setPaintTicks(true);
		drawSize.setPaintLabels(true);
		
		// second slider: pitch adjuster
		final int PITCH_MIN = 1;
		final int PITCH_MAX = 100;
		
		JSlider pitchSlider = new JSlider(JSlider.HORIZONTAL,
	           PITCH_MIN, PITCH_MAX, 50);
		pitchSlider.addChangeListener(this);
		
		pitchSlider.setMajorTickSpacing(24);
		pitchSlider.setMinorTickSpacing(14);
		pitchSlider.setPaintTicks(true);
		pitchSlider.setPaintLabels(true);
		
		// third slider: volume adjuster
		final int VOL_MIN = 1;
		final int VOL_MAX = 100;
		
		JSlider volumeSlider = new JSlider(JSlider.HORIZONTAL,
	           VOL_MIN, VOL_MAX, 50);
		volumeSlider.addChangeListener(this);
		
		volumeSlider.setMajorTickSpacing(24);
		volumeSlider.setMinorTickSpacing(14);
		volumeSlider.setPaintTicks(true);
		volumeSlider.setPaintLabels(true);
		
		/** 
		 * Create three body panels: Main back panel, top panel for labels, mid label for 
		 *  drawing screen, bottom label for adjustment screens 
		 */
		
		// create the first body panel
		mainPanel.setLayout(new BorderLayout());
		// create top panel for box 
		topPanel.setBackground(new Color(230,230,250));

		// temporarily set bottom panel color 
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBackground(new Color (184, 168, 221));
		
		
		/** 
		 * Create three bottom panels that have matte filled borders. 
		 */
		
		// create bottom left panel and set an etched border
		bottomLeft.setBackground(new Color(230,230,250));
		bottomLeft.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));
				
		
		// create bottom center panel and set an etched border
		bottomCenter.setBackground(new Color(230,230,250));
		bottomCenter.setBorder( new CompoundBorder(
					BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
					BorderFactory.createEtchedBorder()));
					
		// create bottom right panel and set an etched border
		bottomRight.setBackground(new Color(230,230,250));
		bottomRight.setBorder( new CompoundBorder(
				BorderFactory.createMatteBorder(10,10,10,10, new Color(75, 0, 130)),
				BorderFactory.createEtchedBorder()));
		
		/** LTButtons (top of screen button options)  
		 * Section includes: Creating the box to hold the top buttons and labels  
		 */
		
		// create box to hold middle top descriptor
		Box LTButtons = Box.createHorizontalBox();
		// box that holds the labels for the drawing screen area
		Box box2 = Box.createHorizontalBox();
		
		/** Left-hand top buttons (save, clear)
		 * Section includes: Creating spacing between left-hand top buttons; adding to the box  
		 */
		
		// add save button to the top box
		LTButtons.add(Box.createHorizontalGlue());
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(saveButton);
		LTButtons.add(Box.createHorizontalStrut(4));
		
		// add clear button to the top box
		LTButtons.add(clearButton);
		LTButtons.add(Box.createHorizontalStrut(280));

		
		/** Create Instrument Mode  
		 * Section includes: Adding the instrument mode label to the top bar's box 
		 */
		
		
		// add screen top label "Create Instrument Mode"
		LTButtons.add(screenTop);
		LTButtons.add(Box.createHorizontalStrut(100));

		/** Right-hand top buttons (play, pause, go to keyboard)
		 * Section includes: Creating spacing between the right-hand top buttons
		 */
		
		// assign text to the right hand buttons
		LTButtons.add(playButton);
		LTButtons.add(Box.createHorizontalStrut(4));
		
		LTButtons.add(pauseButton);
		LTButtons.add(Box.createHorizontalStrut(4));
		
		LTButtons.add(Box.createHorizontalGlue());		
		LTButtons.add(Box.createHorizontalStrut(4));
		LTButtons.add(goToKeyboardButton);
		
		/** Bottom panel label bar  
		 * Section includes: Adding drawing tools, adjuster tools, and instrument select labels to the purple mid-bar
		 */
		
		// add all of the bottom labels to box2 and place box2 at the north of bottomPanel
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));
		box2.add(drawingToolsLabel);
		box2.add(Box.createHorizontalStrut(300));
		box2.add(adjusterLabel);
		box2.add(Box.createHorizontalGlue());
		box2.add(instrumentSelectLabel);
		box2.add(Box.createHorizontalStrut((int)screenSize.getWidth()/70));
		
		/** Instrument list 
		 * Section includes: Creating a list to hold all of the instruments, which will be displayed in the scroller 
		 */
		
		// create instrument scroller panel 
		String[] instruments = {"Instrument 1", "Instrument 2", "Instrument 3", "Instrument 4", "Instrument 5"
				 , "Instrument 6", "Instrument 7"};
		for(String instrument: instruments) {
			instrumentSelectList.addElement(instrument);
		}
		
		/** Instrument Scroller and selection box: Stored in bottom right panel, East 
		 * Section includes: Parameters for instrument spacing, row count, and scroll types 
		 */
		
		// create the list of instruments to go inside of the instrument scroller. set row count and width/height parameters.
		instrumentsList = new JList(instrumentSelectList);
		instrumentsList.setVisibleRowCount(4);
		instrumentsList.setFixedCellHeight(60);
		instrumentsList.setFixedCellWidth(150);
		instrumentsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// stick the list into the scrolling box 
		instrumentScroller = new JScrollPane(instrumentsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		// add the scrolling box to the main panel, bottom right 
		bottomRight.add(instrumentScroller, BorderLayout.EAST);

		/** LTButtons (top of screen button options)  
		 * Section includes: Adding the existing LTButtons box, which contains the buttons themselves, to the top 
		 */
		
		// add buttons to the very top of the screen 
		topPanel.add(LTButtons);
		
		/** Color Chooser 
		 * Section includes: Adding the color chooser in to the bottom left of the screen 
		 */
		
		//add color chooser to bottom panel 
		ColorChooser cc = new ColorChooser();
		bottomLeft.add(cc, BorderLayout.CENTER);
		
		/** TODO: Adjuster buttons (TEMPORARY PLACEMENT IN THE WRONG SECTION, UNTIL I'VE FIGURED OUT HOW TO ADD THEM TO THE 
		 * DRAWING TOOLS BOX THAT ALREADY CONTAINS THE COLOR CHOOSER) 
		 * Section includes: Adding adjuster buttons from separate class
		 */
		JButton squareToggle = new JButton("Square"); 
		bottomCenter.add(squareToggle);
		JButton lineToggle = new JButton("Line"); 
		bottomCenter.add(lineToggle);
		bottomCenter.add(drawSize);
		
		/** Processing display 
		 * Section includes: Adding processing display from VisualStuff to the center panel for use
		 */
		
		//add processing display
		//centerPanel = VisualStuff.getDrawing(400, 200);
		JPanel display = VisualStuff.getDrawing(200, 200);
		centerPanel.add(display);
		
		//Play button, just for testing?
		String[] args = {"ProcessingAudio "};
		PApplet.runSketch(args, audioPlayer);
		
		playButton.addChangeListener(new ChangeListener() {
			@Override
		    public void stateChanged(ChangeEvent e) { 
				JButton source= (JButton)e.getSource();
				if (source.getModel().isPressed()){
			//gets parameters from existing image; all in the range 0 to 1
		    	float[] params = audioPlayer.makeParams(VisualStuff.img);//sneaky public member access, probably not great. 
		    	String wavetype;
		    	if  (params[3] < 1.0/5){
		    		wavetype = "sine";
		    	}else if (params[3] < 2.0/5){
		    		wavetype = "triangle";
		    	}else if (params[3] < 3.0/5){
		    		wavetype = "square";
		    	}else if (params[3] < 4.0/5){
		    		wavetype = "pulse";
		    	}else{
		    		wavetype = "saw";
		    	}
		    	//frequency based off of brightness, rounded to the nearest quarter step, in the range A2 - A4 (i think)
		    	float freq = 220f *(float) Math.pow(2f, ((int)(params[5]*12 +params[6]*24 ))/24);
		    	//envelope (probably needs some work)
		    	float [] env= new float[]{
		    		params[0],
		    		params[6]/4,
		    		params[2]*10,
		    		params[1],
		    	};
		    	//makes a sound with the parameters.
		    	ProcessingAudio.sounds ss = audioPlayer.createsounds(wavetype, (int)freq, 1);
		    	//map with the envelope
		    	audioPlayer.mapkey(' ', ss, env);
		    			    	
		    	//simulate keypress
		    	audioPlayer.KeyPressed=true;
		    	audioPlayer.Key = ' ' ;
				} else{
					audioPlayer.KeyPressed=false;
				}
			}
		});
		
		
		/** Bottom panel specifics & main panel additions 
		 * Section includes: Adding the box of labels, the east/west/center panels on the bottom panel that contain 
		 * adjusters and selectors, and adding the top, center, and bottom panels to the main panels 
		 */
		
		
		// add in all of the bottom panel specifics: box with labels and three panels
		bottomPanel.add(box2, BorderLayout.NORTH);
		bottomPanel.add(bottomRight, BorderLayout.EAST);
		bottomPanel.add(bottomLeft, BorderLayout.WEST);
		bottomPanel.add(bottomCenter, BorderLayout.CENTER);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		//bottomLeft.setPreferredSize(new Dimension(300, 250));
		//bottomCenter.setPreferredSize(new Dimension(300, 250));
		//bottomRight.setPreferredSize(new Dimension(300, 250));
		this.add(mainPanel);
		
		//topButtons.add(topButtonsNest, BorderLayout.NORTH);
		this.setVisible(true); 
		this.pack();
	}

	private void clear() {
		/*for (int i=0; i<VisualStuff.WIDTH; i++) {
			for (int j=0; j<VisualStuff.HEIGHT; j++) {
				VisualStuff.img.setRGB(i, j, 0);
			}
		}*/
		VisualStuff.isCleared = true; 
	}
	
	// slider values 
	public void stateChanged(ChangeEvent e) {
	    JSlider drawSource = (JSlider)e.getSource();
	    int newSize = (int)drawSource.getValue();
	    if (!drawSource.getValueIsAdjusting()) {
	       VisualStuff.sizeValue = newSize; 
	    }
	}
	
	public static void main(String[] args) {
		new Attempt3();
	}
}